package com.javalord.welcome;

import com.javalord.welcome.model.Role;
import com.javalord.welcome.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WelcomeApplication {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(WelcomeApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader() {
		return args -> {
			Role role1 = new Role();
			role1.setName("ROLE_STUDENT");

			Role role2 = new Role();
			role2.setName("ROLE_TEACHER");

			Role role3 = new Role();
			role3.setName("ROLE_PRINCIPAL");

			Role role4 = new Role();
			role4.setName("ROLE_ADMIN");

			roleRepository.saveAll(List.of(role1, role2, role3, role4));
		};
	}

}
