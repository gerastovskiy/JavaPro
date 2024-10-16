package ru.cource.task1.test;

import lombok.Getter;
import org.opentest4j.AssertionFailedError;
import java.lang.reflect.Method;

public class TestResult {
    @Getter
    private Result result;
    private String name;
    @Getter
    private Method method;
    private AssertionFailedError error;

    public TestResult(Result result, String name) {
        this.result = result;
        this.name = name;
    }

    public TestResult(Result result, String name, AssertionFailedError error) {
        this.result = result;
        this.name = name;
        this.error = error;
    }

    public TestResult(Result result, String name, Method method) {
        this.result = result;
        this.name = name;
        this.method = method;
    }

    public TestResult(Result result, String name, Method method, AssertionFailedError error) {
        this.result = result;
        this.name = name;
        this.method = method;
        this.error = error;
    }

    @Override
    public String toString() {
        return result + ". " + name + "(" + method + "): " + error;
    }
}
