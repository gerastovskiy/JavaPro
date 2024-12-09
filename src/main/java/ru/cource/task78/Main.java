package ru.cource.task78;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

/*
task-7
 - Реализовать миграцию баз данных с помощью Flyway
 - Необходимо перенести логику работы с БД на Spring Data JPA и в платежном сервисе, и в сервисе продуктов
 */
@SpringBootApplication(scanBasePackages = "ru.cource.task78")
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class,args);
    }
}
