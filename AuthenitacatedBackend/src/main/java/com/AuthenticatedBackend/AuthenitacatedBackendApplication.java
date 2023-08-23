package com.AuthenticatedBackend;

import com.AuthenticatedBackend.model.ApplicationUser;
import com.AuthenticatedBackend.model.Role;
import com.AuthenticatedBackend.repository.RoleRepository;
import com.AuthenticatedBackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenitacatedBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenitacatedBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args ->  {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role amdinRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(amdinRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
