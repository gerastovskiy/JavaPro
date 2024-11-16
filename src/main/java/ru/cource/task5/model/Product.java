package ru.cource.task5.model;

import lombok.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @NonNull
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private String accountNumber; // в отечественном балансе счетов допустимы буквы, поэтому не Long
    @NonNull
    private BigDecimal accountBalance;
    @NonNull
    private ProductType productType;
}