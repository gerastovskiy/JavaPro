package ru.cource.task4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.cource.task4.model.User;
import ru.cource.task4.service.UserService;
import java.sql.SQLException;

/*
- Разверните локально postgresql БД, создайте таблицу users (id bigserial primary key, username varchar(255) unique);
- Создайте Maven проект и подключите к нему: драйвер postgresql, hickaricp, spring context.
- Создайте пул соединений в виде Spring бина
- Создайте класс User (Long id, String username)
- Реализуйте в виде бина класс UserDao который позволяет выполнять CRUD операции над пользователями
- Реализуйте в виде бина UserService, который позволяет: создавать, удалять, получать одного, получать всех пользователей из базы данных
- Создайте Spring Context, получите из него бин UserService и выполните все возможные операции
*/
public class Main {
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext("ru.cource.task4");

        System.out.println(ctx.getBean(UserService.class).getUser("Alex"));
        System.out.println(ctx.getBean(UserService.class).getAllUsers());
        ctx.getBean(UserService.class).createUser(new User(0L, "Dalex"));
        System.out.println(ctx.getBean(UserService.class).getAllUsers());
        ctx.getBean(UserService.class).deleteUser("Dalex");
        System.out.println(ctx.getBean(UserService.class).getAllUsers());

        ctx.close();
    }
}
