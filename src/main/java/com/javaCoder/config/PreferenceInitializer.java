package com.javaCoder.config;

import com.javaCoder.model.Preference;
import com.javaCoder.model.Vehicle;
import com.javaCoder.repository.PreferenceRepository;
import com.javaCoder.repository.VehicleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PreferenceInitializer {

    private final PreferenceRepository repo;

    @PostConstruct
    public void init() {
        if (repo.count() == 0) {
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
            System.out.println("Vehicle data inserted");
        }
    }
}

// @Bean
// CommandLineRunner init(PreferenceRepository repo) {
// return args -> {
// if (repo.count() == 0) {
// repo.saveAll(List.of(
// new Preference() {{ setPreferenceName("Music"); }},
// new Preference() {{ setPreferenceName("Movies"); }},
// new Preference() {{ setPreferenceName("Travel"); }},
// new Preference() {{ setPreferenceName("Sports"); }}
// ));
// }
// };
// }