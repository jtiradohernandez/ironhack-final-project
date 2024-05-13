package com.example.ironproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class IronprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IronprojectApplication.class, args);
	}
	/*@Bean
	CommandLineRunner run(UserService userService) {
		InitialSetup init = new InitialSetup();
		return args -> {
			init.createAll();
		};
	}*/
}
