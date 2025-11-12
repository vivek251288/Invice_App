package com.javaCoder;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.javaCoder.model.Preference;
import com.javaCoder.repository.PreferenceRepository;

@SpringBootApplication
public class InvicePdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvicePdfApplication.class, args);
	}

	// @Bean
	// CommandLineRunner init(PreferenceRepository repo) {
	// return args -> {
	// if (repo.count() == 0) {
	// repo.saveAll(List.of(
	// new Preference() {
	// {
	// setPreferenceName("Music");
	// }
	// },
	// new Preference() {
	// {
	// setPreferenceName("Movies");
	// }
	// },
	// new Preference() {
	// {
	// setPreferenceName("Travel");
	// }
	// },
	// new Preference() {
	// {
	// setPreferenceName("Sports");
	// }
	// }));
	// }
	// };
	// }

}
