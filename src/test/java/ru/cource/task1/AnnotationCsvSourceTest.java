package ru.cource.task1;

import ru.cource.task1.annotation.CsvSource;

public class AnnotationCsvSourceTest {
    @CsvSource("10,Java,20,true")
    public void testMethod(int a, String b, int c, boolean d) { }
}
