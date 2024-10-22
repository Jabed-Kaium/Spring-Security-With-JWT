package com.example.securityimpljwt;

import com.example.securityimpljwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityImplJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityImplJwtApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init() {
		return (args) -> {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			userRepository.save(user);

			System.out.println("Admin user created");
		};
	}
}
