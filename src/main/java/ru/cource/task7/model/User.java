package ru.cource.task7.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Клиенты")
public class User {
    @NonNull
    private Long id;
    @NonNull
    private String username;
}