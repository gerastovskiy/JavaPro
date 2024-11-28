package ru.cource.task7.service.impl;

import org.springframework.stereotype.Service;
import ru.cource.task7.model.Product;
import ru.cource.task7.paymentCheckers.Checker;
import ru.cource.task7.service.PaymentService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final List<Checker> checkers;

    public PaymentServiceImpl(List<Checker> checkers) {
        this.checkers = checkers;
    }

    @Override
    public void checkDebit(Product product, BigDecimal amount) {
        checkers.forEach(checker -> checker.check(product, amount));
    }

    @Override
    public void checkCredit(Product product, BigDecimal amount) {

    }
}
