package ru.cource.task78.service;

import ru.cource.task78.model.Limit;
import java.math.BigDecimal;

public interface LimitService {
    Limit creditLimit(Long userId, BigDecimal payment);
    Limit debitLimit(Long userId, BigDecimal payment);
    Limit getLimit(Long userId);
    void setDefaultLimit();
}