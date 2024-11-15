package ru.cource.task4.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @NonNull
    private Long id;
    @NonNull
    private String username;
}
