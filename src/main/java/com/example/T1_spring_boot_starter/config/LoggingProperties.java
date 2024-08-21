package com.example.T1_spring_boot_starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Configuration properties for logging HTTP requests and responses.
 */
@ConfigurationProperties(prefix = "logging.starter")
public class LoggingProperties {

    /**
     * Enable logging of HTTP requests.
     */
    private boolean logRequest = true;

    /**
     * Enable logging of HTTP responses.
     */
    private boolean logResponse = true;

    public boolean isLogRequest() {
        return logRequest;
    }

    public void setLogRequest(boolean logRequest) {
        this.logRequest = logRequest;
    }

    public boolean isLogResponse() {
        return logResponse;
    }

    public void setLogResponse(boolean logResponse) {
        this.logResponse = logResponse;
    }
}
