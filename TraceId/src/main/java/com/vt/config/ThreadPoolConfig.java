package com.vt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {


    public static final ThreadLocal<String> local = new ThreadLocal<>();

    @Bean
    public ThreadPoolTaskExecutor myThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor myThreadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        myThreadPoolTaskExecutor.setCorePoolSize(5);
        myThreadPoolTaskExecutor.setMaxPoolSize(10);
        myThreadPoolTaskExecutor.setQueueCapacity(25);
        myThreadPoolTaskExecutor.setTaskDecorator(new MyTaskDecorator());
        return myThreadPoolTaskExecutor;
    }

    private static class MyTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            String mainThreadData = local.get();
            return () -> {
                try {
                    local.set(mainThreadData);
                    runnable.run();
                }finally {
                    local.remove();
                }
            };
        }
    }
}
