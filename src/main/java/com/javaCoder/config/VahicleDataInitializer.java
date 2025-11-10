package com.javaCoder.config;

import com.javaCoder.model.Vehicle;
import com.javaCoder.repository.VehicleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VahicleDataInitializer {

    private final VehicleRepository vehicleRepository;

    @PostConstruct
    public void init() {
        if (vehicleRepository.count() == 0) {

            vehicleRepository.save(new Vehicle(
                    null,
                    "Toyota",
                    "Camry",
                    28000.0,
                    1L // assuming dealerId = 1
            ));

            vehicleRepository.save(new Vehicle(
                    null,
                    "Honda",
                    "Civic",
                    25000.0,
                    2L // assuming dealerId = 2
            ));

            vehicleRepository.save(new Vehicle(
                    null,
                    "Ford",
                    "F-150",
                    40000.0,
                    3L // assuming dealerId = 3
            ));

            System.out.println("✅ Sample vehicle data inserted successfully!");
        }
    }
}
