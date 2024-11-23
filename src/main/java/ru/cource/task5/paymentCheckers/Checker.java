package ru.cource.task5.paymentCheckers;

import ru.cource.task5.model.Product;
import java.math.BigDecimal;

public interface Checker {
    void check(Product product, BigDecimal amount);
}
