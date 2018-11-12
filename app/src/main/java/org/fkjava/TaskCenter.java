package org.fkjava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskCenter {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public static <T> Future<T> commit(Callable<T> task) {
        return threadPool.submit(task);
    }

    public static void commit(Runnable task) {
        threadPool.submit(task);
    }
}
