package ru.cource.task78;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.sql.SQLException;

/*
task-7
 - Реализовать миграцию баз данных с помощью Flyway
 - Необходимо перенести логику работы с БД на Spring Data JPA и в платежном сервисе, и в сервисе продуктов
 */
/*
task-8
Реализовать REST микросервис лимитов, который имеет следующих функционал:
 - Для каждого юзера в БД хранится дневной лимит возможных платежей (первоначально 10000.00. Считаем, что раз в несколько месяцев он может меняться)
 - В 00.00 каждого дня лимит для всех пользователей должен быть сброшен
 - При успешном проведении платежа лимит должен быть уменьшен на соответствующую сумму
 - Если вдруг платеж по какой-то причине не прошел, необходимо иметь возможность восстановить списанный лимит (тут сами выбираете стратегию уменьшения/восстановления лимитов)
 - Поскольку сервиса клиентов у нас нет, в БД храним лимиты для «клиентов» с ID 1-100
 - Поскольку считаем, что gateway не пропустит в систему несуществующего клиента, то при запросе лимита с ID, который отсутствует в БД, создаем новую запись под него со стандартным значением лимита
 */
@SpringBootApplication(scanBasePackages = "ru.cource.task78")
@EnableScheduling
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class,args);
    }
}
