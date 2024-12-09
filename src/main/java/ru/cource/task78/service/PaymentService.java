package ru.cource.task78.service;

import ru.cource.task78.model.Product;
import java.math.BigDecimal;

public interface PaymentService {
    void checkDebit(Product product, BigDecimal amount);
    void checkCredit(Product product, BigDecimal amount);
}
