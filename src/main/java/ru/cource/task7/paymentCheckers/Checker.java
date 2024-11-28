package ru.cource.task7.paymentCheckers;

import ru.cource.task7.model.Product;

import java.math.BigDecimal;

public interface Checker {
    void check(Product product, BigDecimal amount);
}
