package ru.cource.task78.paymentCheckers;

import org.springframework.stereotype.Component;
import ru.cource.task78.model.Product;
import java.math.BigDecimal;

@Component
public class AmountChecker implements Checker {
    @Override
    public void check(Product product, BigDecimal amount) {
        if (product.getAccountBalance().subtract(amount).compareTo(BigDecimal.valueOf(0L)) < 0) throw new IllegalArgumentException("The balance on the account is insufficient");
    }
}
