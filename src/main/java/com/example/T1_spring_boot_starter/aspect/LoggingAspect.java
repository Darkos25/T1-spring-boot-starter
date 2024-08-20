package com.example.T1_spring_boot_starter.aspect;


import com.example.T1_spring_boot_starter.config.LoggingProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final LoggingProperties loggingProperties;

    public LoggingAspect(LoggingProperties loggingProperties) {
        this.loggingProperties = loggingProperties;
    }

    @Around("@annotation(requestMapping)")
    public Object logAround(ProceedingJoinPoint joinPoint, RequestMapping requestMapping) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        long startTime = System.currentTimeMillis();

        try {
            logRequest(request);
            Object result = joinPoint.proceed();
            logResponse(response, System.currentTimeMillis() - startTime);
            return result;
        } catch (Throwable throwable) {
            logResponse(response, System.currentTimeMillis() - startTime);
            throw throwable;
        }
    }


    private void logRequest(HttpServletRequest request) {
        if (loggingProperties.isLogRequest()) {
            logger.info("HTTP REQUEST: method={}, uri={}, headers={}, body={}",
                    request.getMethod(), request.getRequestURI(),
                    getHeaders(request), request.getQueryString());
        }
    }

    private void logResponse(HttpServletResponse response, long duration) {
        if (loggingProperties.isLogResponse()) {
            logger.info("HTTP RESPONSE: status={}, headers={}, duration={}ms",
                    response.getStatus(), response.getHeaderNames(), duration);
        }
    }

    private String getHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append("=").append(request.getHeader(headerName)).append("; ");
        }
        return headers.toString();
    }
}
