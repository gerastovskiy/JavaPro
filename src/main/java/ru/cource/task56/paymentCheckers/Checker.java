package ru.cource.task56.paymentCheckers;

import ru.cource.task56.model.Product;
import java.math.BigDecimal;

public interface Checker {
    void check(Product product, BigDecimal amount);
}
