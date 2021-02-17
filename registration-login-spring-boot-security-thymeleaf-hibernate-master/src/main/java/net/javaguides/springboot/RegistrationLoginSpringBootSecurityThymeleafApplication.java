package net.javaguides.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegistrationLoginSpringBootSecurityThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginSpringBootSecurityThymeleafApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {

		return Args ->{

			System.out.println("########################### \n" +
					"KAPSIKIGESTOCK LOGIN START \n" +
					"###########################");


		};
	}

}
