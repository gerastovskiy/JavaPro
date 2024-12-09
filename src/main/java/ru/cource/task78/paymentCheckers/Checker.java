package ru.cource.task78.paymentCheckers;

import ru.cource.task78.model.Product;
import java.math.BigDecimal;

public interface Checker {
    void check(Product product, BigDecimal amount);
}
