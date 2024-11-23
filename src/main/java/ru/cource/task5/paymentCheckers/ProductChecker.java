package ru.cource.task5.paymentCheckers;

import org.springframework.stereotype.Component;
import ru.cource.task5.model.Product;
import java.math.BigDecimal;

@Component
public class ProductChecker implements Checker{
    @Override
    public void check(Product product, BigDecimal amount) {
        if (product.getId().equals(null)) throw new IllegalArgumentException("Product id is null!");

        if (product.getUserId().equals(null)) throw new IllegalArgumentException("User id is null!");

        if (product.getAccountBalance().equals(null)) throw new IllegalArgumentException("AccountBalance is null!");
    }
}
