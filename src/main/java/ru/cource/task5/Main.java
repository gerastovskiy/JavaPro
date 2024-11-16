package ru.cource.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

/*
Создайте сервис, который хранит продукты клиентов.
- Хранение продуктов необходимо организовать как в прошлом домашнем задании (пока не подключаем стартеры для работы с БД).
- Продукт клиента: id, номер счета, баланс, тип продукта (счет, карта).
- Сервис должен хранить продукты.
- Сервис должен давать возможность: запросить все продукты по userId, запросить продукт по productId.
 */
@SpringBootApplication(scanBasePackages = "ru.cource.task5")
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class,args);

        // было для демонстрации работы сервисов, удалено после добавления контроллеров
        // user
//        System.out.println(ctx.getBean(UserService.class).getUser("Alex"));
//        System.out.println(ctx.getBean(UserService.class).getAllUsers());
//        ctx.getBean(UserService.class).createUser(new User(0L, "Dalex"));
//        System.out.println(ctx.getBean(UserService.class).getAllUsers());
//        ctx.getBean(UserService.class).deleteUser("Dalex");
//        System.out.println(ctx.getBean(UserService.class).getAllUsers());

        // product
//        System.out.println(ctx.getBean(ProductService.class).getProduct("40817810000000000001"));
//        System.out.println(ctx.getBean(ProductService.class).getProductsByUser(1L));
//        System.out.println(ctx.getBean(ProductService.class).getAllProducts());
//        ctx.getBean(ProductService.class).createProduct(new Product(0L, 1L, "40817810000000000999", BigDecimal.valueOf(999.99), ProductType.ACCOUNT));
//        System.out.println(ctx.getBean(ProductService.class).getAllProducts());
//        ctx.getBean(ProductService.class).deleteProduct("40817810000000000999");
//        System.out.println(ctx.getBean(ProductService.class).getAllProducts());
    }
}
