package ru.cource.task1;

import ru.cource.task1.test.AnnotationsTest;
import ru.cource.task1.test.TestResult;
import ru.cource.task1.test.TestRunner;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/*
Реализовать приложение, способное «прогонять тесты».
· Создаете набор аннотаций: @Test, @BeforeSuite, @AfterSuite, которые могут добавляться на методы, @Test на обычные методы, @BeforeSuite и @AfterSuite только на статические.
· Для аннотации @Test добавляете параметр priority (int в пределах от 1 до 10 включительно). Если параметр не задан явно, то он равен 5.
· Создаете класс с набором методов, и размечаете их созданными аннотациями.
· Создаете класс TestRunner со статическим методом runTests(Class c).
· Выполнение runTests(Class c) приводит к следующему:
Указанный класс загружается в память
 o Происходит проверка что методов с аннотациями @BeforeSuite не больше одного
 o Происходит проверка что методов с аннотациями @AfterSuite не больше одного
 o Примечание: Можете выполнить дополнительные проверки, которые посчитаете нужными
 o Выполняется метод с аннотацией @BeforeSuite, если такой есть
 o Выполняются методы с аннотациями @Test в соответствии с их приоритетом. Вначале выполняются те методы, у которых приоритет выше.
 o Выполняется метод с аннотацией @AfterSuite, если такой есть
* Можете добавить аннотации @BeforeTest и @AfterTest, методы с такими аннотациями должны выполняться перед каждым и после каждого теста соответственно.
* Добавьте аннотацию @CsvSource параметром которой является строка. При запуске метода-теста эта строка должна распарситься, каждый элемент строки приведен к типу соответствующего аргумента метода и метод выполнен с данными из указанной строки.
Например: @CsvSource("10, Java, 20, true")
public void testMethod(int a, String b, int c, boolean d) { .. }
*/
public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<TestResult> result = TestRunner.runTests(AnnotationsTest.class);
        result.forEach(testResult -> System.out.println(testResult));
    }
}