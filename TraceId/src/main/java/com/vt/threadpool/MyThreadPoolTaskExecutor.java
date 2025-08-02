package com.vt.threadpool;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class MyThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {


    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task,MDC.getCopyOfContextMap()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(wrap(task,MDC.getCopyOfContextMap()));
    }


    private Runnable wrap(Runnable task, Map<String, String> context) {
        return () -> {
            if (context == null ) {
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            try {
                task.run();
            }finally {
                MDC.clear();
            }

        };
    }
    private <T>Callable<T> wrap(Callable<T> task, Map<String, String> context) {
        return () -> {
            if (context == null ) {
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            try {
               return task.call();
            }finally {
                MDC.clear();
            }

        };
    }
}
