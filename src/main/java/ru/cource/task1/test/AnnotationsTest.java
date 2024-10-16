package ru.cource.task1.test;

import ru.cource.task1.annotation.*;

public class AnnotationsTest {
    @Test(priority = 1)
    public void test1() { System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); }

    @Test
    public void test2(){ System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); }

    @BeforeSuite
    public void test3(){ System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); }

    @AfterSuite
    public void test4(){ System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); }

    @CsvSource("10,Java,20,true")
    public void testMethod(int a, String b, int c, boolean d) { System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); }
}
