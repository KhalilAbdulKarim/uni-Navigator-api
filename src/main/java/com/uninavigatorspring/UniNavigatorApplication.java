package com.uninavigatorspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class UniNavigatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(UniNavigatorApplication.class, args);
	}
}
