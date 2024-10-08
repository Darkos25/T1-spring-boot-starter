package com.example.T1_spring_boot_starter.config;


import com.example.T1_spring_boot_starter.aspect.LoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Filter;

/**
 * Auto-configuration for logging HTTP requests and responses.
 */
@Configuration
@ConditionalOnClass(Filter.class)
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAutoConfiguration {

    /**
     * Create a bean for LoggingAspect if logging is enabled in the properties.
     *
     * @param loggingProperties The properties that configure the logging.
     * @return The LoggingAspect bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public LoggingAspect loggingAspect(LoggingProperties loggingProperties) {
        return new LoggingAspect(loggingProperties);
    }
}
