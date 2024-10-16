package ru.cource.task1.test;

import org.opentest4j.AssertionFailedError;
import ru.cource.task1.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    // TODO: подумать над другим хранением более удобным для обработки (возможно, мапа листов по ключу result)
    public static List<TestResult> runTests(Class c) {
        List<TestResult> result = new ArrayList<>();

        testCheck(c, result);
        testRun(c, result);

        return result;
    }

    private static Object convert(String from){
        if (from.matches("-?\\d+")) {
            return Integer.parseInt(from);
        } else if (from.matches("-?\\d+(\\.\\d+)?")) {
            return Double.parseDouble(from);
        } else if (from.matches("true|false|0|1")) {
            return Boolean.parseBoolean(from);
        } else return from;
    }

    private static void testCheck(Class c, List<TestResult> result){
        Method [] methods = c.getMethods();

        // Происходит проверка что методов с аннотациями @BeforeSuite не больше одного
        var methodCount = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(BeforeSuite.class))
                .count();
        if (methodCount > 1) result.add(new TestResult(Result.FAILED, "BeforeSuite", new AssertionFailedError("Count > 1")));
        else result.add(new TestResult(Result.PASSED, "BeforeSuite"));

        // Происходит проверка что методов с аннотациями @AfterSuite не больше одного
        methodCount = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AfterSuite.class))
                .count();
        if (methodCount > 1) result.add(new TestResult(Result.FAILED, "AfterSuite", new AssertionFailedError("Count > 1")));
        else result.add(new TestResult(Result.PASSED, "AfterSuite"));
    }

    private static void testRun(Class c, List<TestResult> result)  {
        Method [] methods = c.getMethods();
        Object object;
        try {
            object = c.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | RuntimeException | NoSuchMethodException e) {
            result.add(new TestResult(Result.ERROR, "Get Declared Constructor", new AssertionFailedError("Init Error", e)));
            return;
        }

        // Выполняется метод с аннотацией @BeforeSuite, если такой есть
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(BeforeSuite.class))
                .forEach(method -> {
                    try {
                        method.invoke(object);
                        result.add(new TestResult(Result.PASSED, "BeforeSuite", method));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        result.add(new TestResult(Result.ERROR, "BeforeSuite", method, new AssertionFailedError(null, e)));
                    }
                });

        // Выполняются методы с аннотациями @Test в соответствии с их приоритетом
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparing(method -> method.getAnnotation(Test.class).priority()))
                .forEach(method -> {
                    try {
                        method.invoke(object);
                        result.add(new TestResult(Result.PASSED, "Test", method));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        result.add(new TestResult(Result.ERROR, "Test", method, new AssertionFailedError(null, e)));
                    }
                });

        // При запуске метода-теста эта строка должна распарситься, каждый элемент строки приведен к типу соответствующего аргумента метода
        // и метод выполнен с данными из указанной строки.
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(CsvSource.class))
                .forEach(method -> {
                    try {
                        String [] tmpArgs = method.getAnnotation(CsvSource.class).value().split(",");
                        Object [] args = Arrays.stream(tmpArgs).map(TestRunner::convert).toArray();

                        method.invoke(object, args);
                        result.add(new TestResult(Result.PASSED, "CsvSource", method));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        result.add(new TestResult(Result.ERROR, "CsvSource", method, new AssertionFailedError(null, e)));
                    }
                });

        // Выполняется метод с аннотацией @AfterSuite, если такой есть
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AfterSuite.class))
                .forEach(method -> {
                    try {
                        method.invoke(object);
                        result.add(new TestResult(Result.PASSED, "AfterSuite", method));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        result.add(new TestResult(Result.ERROR, "AfterSuite", method, new AssertionFailedError(null, e)));
                    }
                });
    }
}