package ru.cource.task5.datasource;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
public class HikariDataSource {
    private static com.zaxxer.hikari.HikariDataSource dataSource;

    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = HikariDataSource.class.getClassLoader()
                .getResourceAsStream("task5/application.yaml");
        Map<String, String> dsSettings = yaml.load(inputStream);

        try {
            dataSource = new com.zaxxer.hikari.HikariDataSource();
            dataSource.setDriverClassName(dsSettings.get("driver-class-name"));
            dataSource.setJdbcUrl(dsSettings.get("url"));
            dataSource.setUsername(dsSettings.get("username"));
            dataSource.setPassword(dsSettings.get("password"));
            dataSource.setMinimumIdle(100);
            dataSource.setMaximumPoolSize(2000);
            dataSource.setAutoCommit(false);
            dataSource.setLoginTimeout(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
}