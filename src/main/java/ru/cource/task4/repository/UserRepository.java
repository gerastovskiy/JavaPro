package ru.cource.task4.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cource.task4.datasource.HikariDataSource;
import ru.cource.task4.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserRepository {
    private final HikariDataSource dataSource;

    @Autowired
    public UserRepository(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createUser(User user) throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate(String.format("insert into users(username) values('%s')",user.getUsername()));
        cn.commit();
    }

    public void deleteUser(String username) throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate(String.format("delete from users where username = '%s'", username));
        cn.commit();
    }

    public User getUser(String username) throws SQLException {
        var st = dataSource.getConnection().createStatement();
        var rs = st.executeQuery(String.format("select id, username from users where username = '%s'", username));
        while (rs.next()) {
            if (!rs.isLast()) throw new IllegalStateException ("Record More than One by username " + username);
            return new User(rs.getLong(1), rs.getString(2));
        }

        throw new NoSuchElementException("Record Not Found by username " + username);
    }

    public List<User> getAllUsers() throws SQLException {
        var st = dataSource.getConnection().createStatement();
        var rs = st.executeQuery("select id, username from users");
        List<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(new User(rs.getLong(1), rs.getString(2)));
        }

        return users;
    }

    public void deleteAllUsers() throws SQLException {
        var cn = dataSource.getConnection();
        var st = cn.createStatement();
        var rs = st.executeUpdate("delete from users");
        cn.commit();
    }
}
