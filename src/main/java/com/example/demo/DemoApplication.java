package com.example.demo;


import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {

	private ApplicationEventPublisher publisher;

	public DemoApplication(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@ApplicationModuleListener
	public void onCustomEvent(CustomEvent event) {
		System.out.println("Event received!");
	}

	@Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 5)
	public void fireEvents() {
		System.out.println("firing event");
		publisher.publishEvent(new CustomEvent());
	}

	record CustomEvent() {}
}
