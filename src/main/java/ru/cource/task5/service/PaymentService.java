package ru.cource.task5.service;

import ru.cource.task5.model.Product;
import java.math.BigDecimal;

public interface PaymentService {
    void checkDebit(Product product, BigDecimal amount);
    void checkCredit(Product product, BigDecimal amount);
}
