package ru.cource.task7.service;

import ru.cource.task7.model.Product;

import java.math.BigDecimal;

public interface PaymentService {
    void checkDebit(Product product, BigDecimal amount);
    void checkCredit(Product product, BigDecimal amount);
}
