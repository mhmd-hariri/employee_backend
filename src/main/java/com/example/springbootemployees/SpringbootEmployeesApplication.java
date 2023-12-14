package com.example.springbootemployees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootEmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEmployeesApplication.class, args);
	}

}
