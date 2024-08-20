package com.example.T1_spring_boot_starter;

import com.example.T1_spring_boot_starter.bean.ExampleBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class T1SpringBootStarterApplication {

	private final ExampleBean exampleBean;

	public static void main(String[] args) {
		SpringApplication.run(T1SpringBootStarterApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReady() {
		new Thread(exampleBean::lockWith).start();
		new Thread(exampleBean::lockWith).start();
		new Thread(exampleBean::lockWith).start();
	}

}
