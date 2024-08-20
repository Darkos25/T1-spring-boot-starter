package com.example.T1_spring_boot_starter.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExampleBean {

    public void lockWith() {
        log.info("lockWith");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
