package ru.cource.task5.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Платежи")
public class Payment {
    @NonNull
    @Schema(description = "id продукта, не null")
    private Long productId;
    @NonNull
    @Min(0)
    @Schema(description = "сумма платежа, больше 0")
    private BigDecimal amount;
}
