package ru.cource.task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOperations {
    public static int getMax3Int(List<Integer> lst){
        return lst.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .summaryStatistics().getMin();
    }

    public static List<Integer> removeDuplicatesV1(List<Integer> lst){
        return lst.stream()
                .distinct()
                .toList();
    }

    public static List<Integer> removeDuplicatesV2(List<Integer> lst){
        return lst.stream()
                .collect(Collectors.toSet())
                .stream().toList();
    }

    public static int getMax3UniqInt(List<Integer> lst){
        return lst.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .summaryStatistics().getMin();
    }

    public static List<String> get3OldestEngineer(List<Employee> lst){
        return lst.stream()
                .filter(employee -> employee.getJobTitle() == JobTitle.ENGINEER)
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .toList();
    }

    public static double getAverageEngineerAge(List<Employee> lst){
        return lst.stream()
                .filter(employee -> employee.getJobTitle() == JobTitle.ENGINEER)
                .mapToInt(Employee::getAge)
                .summaryStatistics().getAverage();
    }

    public static String getLongest(List<String> lst){
        return lst.stream()
                .max(Comparator.comparing(String::length))
                .get();
    }

    public static Map<String, Long> getWordCount(String str){
        return Arrays.stream(str.split("\\s+"))
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));
    }

    public static List<String> getSortedWordsByLengthAndAlphabet(List<String> lst){
        return lst.stream()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .toList();
    }

    public static String getLongestWord(List<String> lst){
        return lst.stream()
                .flatMap(Pattern.compile("\\s+")::splitAsStream)
                .max(Comparator.comparing(String::length))
                .get();
    }
}