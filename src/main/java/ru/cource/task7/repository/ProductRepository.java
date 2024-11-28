package ru.cource.task7.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import ru.cource.task7.model.Product;
import ru.cource.task7.model.ProductType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component @DependsOn("dataSource")
public class ProductRepository {
    private final HikariDataSource dataSource;

    public ProductRepository(HikariDataSource dataSource) { this.dataSource = dataSource; }

    public void createProduct(Product product) throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate(String.format("insert into product(account_number, account_balance, product_type, user_id) values('%s', (%s), '%s', %d)"
                ,product.getAccountNumber(), product.getAccountBalance().toString(), product.getProductType(), product.getUserId()));
        cn.commit();
    }

    public void updateProduct(Product product) throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate(String.format("update product set account_number = '%s', account_balance = (%s), product_type = '%s', user_id = %d where id = %d"
                ,product.getAccountNumber(), product.getAccountBalance().toString(), product.getProductType(), product.getUserId(), product.getId()));
        cn.commit();
    }

    public void deleteProduct(String account_number) throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate(String.format("delete from product where account_number = '%s'", account_number));
        cn.commit();
    }

    public Product getProduct(String account_number) throws SQLException {
        var st = dataSource.getConnection().createStatement();
        var rs = st.executeQuery(String.format("select id, user_id, account_number, account_balance, product_type from product where account_number = '%s'", account_number));
        while (rs.next()) {
            if (!rs.isLast()) throw new IllegalStateException ("Record More than One by account_number " + account_number);
            return new Product(rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getBigDecimal(4),
                    ProductType.valueOf(rs.getString(5)));
        }

        throw new NoSuchElementException("Record Not Found by account_number " + account_number);
    }

    public Product getProduct(Long id) throws SQLException {
        var st = dataSource.getConnection().createStatement();
        var rs = st.executeQuery(String.format("select id, user_id, account_number, account_balance, product_type from product where id = %d", id));
        while (rs.next()) {
            if (!rs.isLast()) throw new IllegalStateException ("Record More than One by id " + id);
            return new Product(rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getBigDecimal(4),
                    ProductType.valueOf(rs.getString(5)));
        }

        throw new NoSuchElementException("Record Not Found by id " + id);
    }

    public List<Product> getProductsByUser(Long id) throws SQLException {
        var st = dataSource.getConnection().createStatement();
        var rs = st.executeQuery(String.format("select id, user_id, account_number, account_balance, product_type from product where user_id = %d", id));
        List<Product> products = new ArrayList<>();

        while (rs.next()) {
            products.add(new Product(rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getBigDecimal(4),
                    ProductType.valueOf(rs.getString(5))));
        }
        return products;
    }

    public List<Product> getAllProducts() throws SQLException {
        var st = dataSource.getConnection().createStatement();
        var rs = st.executeQuery("select id, user_id, account_number, account_balance, product_type from product");
        List<Product> products = new ArrayList<>();

        while (rs.next()) {
            products.add(new Product(rs.getLong(1),
                    rs.getLong(2),
                    rs.getString(3),
                    rs.getBigDecimal(4),
                    ProductType.valueOf(rs.getString(5))));
        }
        return products;
    }

    public void deleteAllProducts() throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate("delete from product");
        cn.commit();
    }
}
