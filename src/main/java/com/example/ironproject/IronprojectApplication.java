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


	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			Role admin = new Role("ROLE_ADMIN");
			Role employee = new Role("ROLE_EMPLOYEE");
			long ms = 900000000;
			Date date1 = new Date(ms);
			Date date2 = new Date(ms);
			userService.saveRole(admin);
			userService.saveRole(employee);
			userService.addEmployee(new Employee("48484848", "Xavi Tirado", date1, Jobs.Reception, admin, "daku", "12345678"));
			userService.addEmployee(new Employee("59595959", "Thais Real", date2, Jobs.Cleaning, employee, "comolainfusion","12345678"));
		};
	}
}
