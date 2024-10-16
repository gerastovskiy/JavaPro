package ru.cource.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.cource.task1.annotation.AfterTest;
import ru.cource.task1.annotation.BeforeTest;
import ru.cource.task1.test.Result;
import ru.cource.task1.test.TestResult;
import ru.cource.task1.test.TestRunner;
import java.util.List;

public class TaskTest {
    @BeforeTest
    public void BeforeTest(){ }
    @AfterTest
    public void AfterTest(){ }

    @Test
    public void annotationsBeforeTest(){
        List<TestResult> result = TestRunner.runTests(AnnotationsBeforeTest.class);

        Assertions.assertEquals(Result.FAILED, result.get(0).getResult());
    }

    @Test
    public void annotationsTest(){
        List<TestResult> result = TestRunner.runTests(AnnotationsTest.class);

        Assertions.assertEquals("test1", result.get(2).getMethod().getName());
        Assertions.assertEquals("test2", result.get(3).getMethod().getName());
    }

   @Test
    public void annotationsAfterTest(){
        List<TestResult> result = TestRunner.runTests(AnnotationsAfterTest.class);

        Assertions.assertEquals(Result.FAILED, result.get(1).getResult());
    }

    @Test
    public void annotationCsvSourceTest(){
        List<TestResult> result = TestRunner.runTests(AnnotationCsvSourceTest.class);

        Assertions.assertEquals("int", result.get(2).getMethod().getParameterTypes()[0].getName());
        Assertions.assertEquals("java.lang.String", result.get(2).getMethod().getParameterTypes()[1].getName());
        Assertions.assertEquals("int", result.get(2).getMethod().getParameterTypes()[2].getName());
        Assertions.assertEquals("boolean", result.get(2).getMethod().getParameterTypes()[3].getName());
    }
}
