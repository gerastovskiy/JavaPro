package ru.cource.task7.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Продукты")
@Table(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private String accountNumber; // в отечественном балансе счетов допустимы буквы, поэтому не Long
    @NonNull
    private BigDecimal accountBalance;
    @NonNull
    @Enumerated(EnumType.STRING)
    private ProductType productType;
}