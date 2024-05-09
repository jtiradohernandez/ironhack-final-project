package com.example.ironproject;

import com.example.ironproject.enumeration.Jobs;
import com.example.ironproject.model.People.Employee;
import com.example.ironproject.model.Security.Role;
import com.example.ironproject.service.People.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class IronprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IronprojectApplication.class, args);
	}

}
