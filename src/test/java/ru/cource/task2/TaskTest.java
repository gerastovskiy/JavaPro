package ru.cource.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static ru.cource.task2.StreamOperations.*;

public class TaskTest {
    @Test
    public void getMax3IntCorrectTest(){
        var lstIn = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        var result = 10;
        Assertions.assertEquals(result, getMax3Int(lstIn));
    }

    @Test
    public void getMax3IntCorrectEmptyTest(){
        var lstIn = List.of(0);
        var result = 0;
        Assertions.assertEquals(result, getMax3Int(lstIn));
    }

    @Test
    public void removeDuplicatesCorrectTestV1(){
        var lstIn = List.of(5, 5, 10, 9, 1, 2, 10, 1, 5);
        var result = List.of(5, 10, 9, 1, 2);
        Assertions.assertEquals(result, removeDuplicatesV1(lstIn));
    }

    @Test
    public void removeDuplicatesWithoutDuplicatesTestV1(){
        var lstIn = List.of(5, 10, 9, 1, 2);
        var result = List.of(5, 10, 9, 1, 2);
        Assertions.assertEquals(result, removeDuplicatesV1(lstIn));
    }

    @Test
    public void removeDuplicatesCorrectTestV2(){
        var lstIn = List.of(5, 5, 10, 9, 1, 2, 10, 1, 5);
        var result = List.of(5, 10, 9, 1, 2);
        Assertions.assertEquals(result, removeDuplicatesV1(lstIn));
    }

    @Test
    public void removeDuplicatesWithoutDuplicatesTestV2(){
        var lstIn = List.of(5, 10, 9, 1, 2);
        var result = List.of(5, 10, 9, 1, 2);
        Assertions.assertEquals(result, removeDuplicatesV1(lstIn));
    }

    @Test
    public void getMax3UniqIntCorrectTest(){
        var lstIn = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        var result = 9;
        Assertions.assertEquals(result, getMax3UniqInt(lstIn));
    }

    @Test
    public void get3OldestEngineerCorrectTest(){
        var lstIn = List.of(
                new Employee("Slava", 20, JobTitle.ENGINEER),
                new Employee("Pete", 49, JobTitle.WEB_DEVELOPER),
                new Employee("Comrade", 90, JobTitle.ENGINEER),
                new Employee("Ivan", 30, JobTitle.JAVA_DEVELOPER),
                new Employee("Eugene", 75, JobTitle.ENGINEER),
                new Employee("Lena", 50, JobTitle.SQL_DEVELOPER),
                new Employee("Alex", 65, JobTitle.ENGINEER),
                new Employee("Boris", 30, JobTitle.FRONT_END_DEVELOPER),
                new Employee("Sveta", 16, JobTitle.NETWORK_ENGINEER)
        );
        var result = List.of("Comrade", "Eugene", "Alex");
        Assertions.assertEquals(result, get3OldestEngineer(lstIn));
    }

    @Test
    public void getAverageEngineerAgeCorrectTest(){
        var lstIn = List.of(
                new Employee("Slava", 20, JobTitle.ENGINEER),
                new Employee("Pete", 49, JobTitle.WEB_DEVELOPER),
                new Employee("Comrade", 90, JobTitle.ENGINEER),
                new Employee("Ivan", 30, JobTitle.JAVA_DEVELOPER),
                new Employee("Eugene", 75, JobTitle.ENGINEER),
                new Employee("Lena", 50, JobTitle.SQL_DEVELOPER),
                new Employee("Alex", 65, JobTitle.ENGINEER),
                new Employee("Boris", 30, JobTitle.FRONT_END_DEVELOPER),
                new Employee("Sveta", 16, JobTitle.NETWORK_ENGINEER)
        );
        var result = 62.5;
        Assertions.assertEquals(result, getAverageEngineerAge(lstIn));
    }

    @Test
    public void getLongestCorrectTest(){
        var lstIn = List.of("One", "Two", "Three", "Four");
        Assertions.assertEquals("Three", getLongest(lstIn));
    }

    @Test
    public void getWordCountCorrectTest(){
        var str = "мама мыла раму маша мыла стол";
        Map<String, Long> result = Map.of("стол", Long.valueOf(1),
                                        "маша",Long.valueOf(1),
                                        "мыла",Long.valueOf(2),
                                        "раму",Long.valueOf(1),
                                        "мама",Long.valueOf(1));
        Assertions.assertEquals(result, getWordCount(str));
    }

    @Test
    public void getSortedWordsByLengthAndAlphabetCorrectTest(){
        var str = List.of("адис-абебаcы", "стол", "рама", "рука", "арифмометр", "адис-абеба");
        var result = List.of("рама", "рука", "стол", "адис-абеба", "арифмометр", "адис-абебаcы");
        Assertions.assertEquals(result, getSortedWordsByLengthAndAlphabet(str));
    }

    @Test
    public void getLongestWordCorrectTest(){
        List<String> words = List.of(
                "раз два три четыре пять", "самолёт пароход трамвай дельтаплан мышь", "1 2 3 4 5", "alex balex calex dalex ealex", "арифмометр дальтоникс линейка"
        );
        var result = getLongestWord(words);
        Assertions.assertTrue(result.equals("дельтаплан") || result.equals("арифмометр") || result.equals("дальтоникс"));
    }
}