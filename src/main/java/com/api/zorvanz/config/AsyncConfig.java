package com.api.zorvanz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {
    @Bean ( name = "threadPoolTaskExecutor" )
    public TaskExecutor getAsyncExecutor () {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor ();
        executor.setCorePoolSize ( 4 );
        executor.setMaxPoolSize ( 8 );
        executor.setQueueCapacity ( 100 );
        // executor.setWaitForTasksToCompleteOnShutdown ( true );
        executor.setThreadNamePrefix ( "AsyncThread-" );
        executor.initialize ();
        return executor;
    }
}
