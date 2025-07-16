package com.wordpress.faeldi.olx_monitor_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wordpress.faeldi.olx_monitor_backend", "com.example.saasmonitor"})
public class OlxMonitorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxMonitorBackendApplication.class, args);
	}

}
