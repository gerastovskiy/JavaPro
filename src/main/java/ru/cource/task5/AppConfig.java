package ru.cource.task5;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
    private final String url;
    private final String username;
    private final String password;
    private final String driver;

    public AppConfig(@Value("${spring.url}")String url,
                     @Value("${spring.username}") String username,
                     @Value("${spring.password}") String password,
                     @Value("${spring.driver-class-name}") String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }

    @Bean
    RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("task5/application.yaml"));
        configurer.setProperties(yamlPropertiesFactoryBean.getObject());

        return configurer;
    }

    @Bean @DependsOn("properties")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setMinimumIdle(100);
        dataSource.setMaximumPoolSize(2000);
        dataSource.setAutoCommit(false);

        return dataSource;
    }
}
