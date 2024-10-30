package ru.cource.task3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
Попробуйте реализовать собственный пул потоков. В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
Как только пул создан, он сразу инициализирует и запускает потоки. Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
При выполнении у пула потоков метода execute(Runnabler), указанная задача должна попасть в очередь исполнения, и как только появится свободный поток – должна быть выполнена.
Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются пулом (при попытке добавить задачу можно бросать IllegalStateException),
и все потоки для которых больше нет задач завершают свою работу.
Дополнительно можно добавить метод awaitTermination() без таймаута, работающий аналогично стандартным пулам потоков.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var pool1 = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.HOURS, new ArrayBlockingQueue<>(10));
//        var pool2 = Executors.newFixedThreadPool(2);
//
//        pool1.shutdown();
//        pool2.shutdown();

        var pool = new MyPoolExecutor(1);

        pool.execute(() -> {
            System.out.println("Задание 1 поставлено в очередь");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Задание 1 завершено");
        });

        pool.execute(() -> {
            System.out.println("Задание 2 поставлено в очередь");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Задание 2 завершено");
        });

        pool.execute(() -> {
            System.out.println("Задание 3 поставлено в очередь");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Задание 3 завершено");
        });

        pool.execute(() -> {
            System.out.println("Задание 4 поставлено в очередь");
            System.out.println("Задание 4 завершено");
        });

        Thread.sleep(2000L);
        pool.shutdown();

        pool.execute(() -> {
            System.out.println("Задание X поставлено в очередь");
            System.out.println("Задание X завершено");
        });

        System.out.println("END");

    }
}