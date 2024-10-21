package ru.cource.task2;

import java.util.List;
import static ru.cource.task2.StreamOperations.*;

/*
Все задачи должны быть выполнены в одну строку.
· Реализуйте удаление из листа всех дубликатов
· Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
· Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
· Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
· Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
· Найдите в списке слов самое длинное
· Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
· Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
· Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
*/
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
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
        List<String> words = List.of(
                "раз два три четыре пять", "самолёт пароход трамвай дельтаплан мышь", "1 2 3 4 5", "alex balex calex dalex ealex", "арифмометр дальтоникс линейка"
        );

        System.out.println("task1: " + getMax3Int(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13)));
        System.out.println("task2v1: " + removeDuplicatesV1(List.of(5, 5, 10, 9, 1, 2, 10, 1, 5)));
        System.out.println("task2v2: " + removeDuplicatesV1(List.of(5, 5, 10, 9, 1, 2, 10, 1, 5)));
        System.out.println("task3: " + getMax3UniqInt(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13)));
        System.out.println("task4: " + get3OldestEngineer(employees));
        System.out.println("task5: " + getAverageEngineerAge(employees));
        System.out.println("task6: " + getLongest(List.of("One", "Two", "Three", "Four")));
        System.out.println("task7: " + getWordCount("мама мыла раму маша мыла стол"));
        System.out.println("task8: " + getSortedWordsByLengthAndAlphabet(List.of("адис-абебаcы", "стол", "рама", "рука", "арифмометр", "адис-абеба")));
        System.out.println("task9: " + getLongestWord(words));
    }
}
