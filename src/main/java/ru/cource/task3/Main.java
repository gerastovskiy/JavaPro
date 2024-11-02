package ru.cource.task3;

import java.util.concurrent.atomic.AtomicInteger;

/*
Попробуйте реализовать собственный пул потоков. В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
Как только пул создан, он сразу инициализирует и запускает потоки. Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
При выполнении у пула потоков метода execute(Runnabler), указанная задача должна попасть в очередь исполнения, и как только появится свободный поток – должна быть выполнена.
Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются пулом (при попытке добавить задачу можно бросать IllegalStateException),
и все потоки для которых больше нет задач завершают свою работу.
Дополнительно можно добавить метод awaitTermination() без таймаута, работающий аналогично стандартным пулам потоков.
 */
public class Main {
    private static AtomicInteger taskCounter = new AtomicInteger(1);

    public static Runnable newRunnable(long delay){
        Runnable r = () ->
        {
            var taskCount = taskCounter.getAndIncrement();
            System.out.println(String.format("Задание %s поставлено в очередь", taskCount));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println(String.format("Задание %s прервано", taskCount));
            }
            System.out.println(String.format("Задание %s завершено", taskCount));
        };
        return r;
    }

    public static void main(String[] args) throws InterruptedException {
        var pool = new MyPoolExecutor(3);

        pool.execute(newRunnable(1000L));
        pool.execute(newRunnable(6000L));
        pool.execute(newRunnable(3000L));
        pool.execute(newRunnable(5000L));
        pool.execute(newRunnable(1000L));
        pool.execute(newRunnable(500L));
        pool.execute(newRunnable(100L));

        Thread.sleep(2000L);
        pool.shutdown();
        //pool.shutdownNow();

        pool.execute(newRunnable(1));
    }
}