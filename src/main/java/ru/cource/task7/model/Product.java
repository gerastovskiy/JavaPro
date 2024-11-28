package ru.cource.task7.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Продукты")
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