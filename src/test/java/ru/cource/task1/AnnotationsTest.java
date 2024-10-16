package ru.cource.task1;

public class AnnotationsTest {
    @ru.cource.task1.annotation.Test(priority = 1)
    public void test1() { }

    @ru.cource.task1.annotation.Test
    public void test2() { }
}
