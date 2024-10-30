package ru.cource.task3;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class MyPoolExecutor extends AbstractExecutorService {
    private final List<MyPoolThread> threads;
    private final List<Runnable> tasks;
    private MyPoolState state;

    public MyPoolExecutor(int corePoolSize) {
        threads = new ArrayList<>();
        tasks = new LinkedList<>();
        state = MyPoolState.RUNNING;

        for (int i = 0; i < corePoolSize; i++) {
            threads.add(new MyPoolThread(tasks));
            threads.stream().forEach(Thread::start);
        }
    }

    @Override
    public void shutdown() {
        state = MyPoolState.TERMINATED;

        threads.stream().forEach(myPoolThread -> {
            try {
                myPoolThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<Runnable> shutdownNow() {
        threads.stream().forEach(Thread::interrupt);
        return tasks;
    }

    @Override
    public boolean isShutdown() {
        return (state == MyPoolState.SHUTDOWN);
    }

    @Override
    public boolean isTerminated() {
        return (state == MyPoolState.TERMINATED);
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void execute(Runnable command) {
        if (state == MyPoolState.RUNNING) {
            synchronized (tasks){
                tasks.add(command);
            }
        } else {
            throw new IllegalStateException(String.format("Состояние пула %s не допускает добавление новых задач.", state));
        }
    }
}
