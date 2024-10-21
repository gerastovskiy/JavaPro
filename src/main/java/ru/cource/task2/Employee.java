package ru.cource.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Employee {
    @Getter
    String name;
    @Getter
    int age;
    @Getter
    JobTitle jobTitle;
}