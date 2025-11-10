package com.javaCoder.config;

import com.javaCoder.model.Dealer;
import com.javaCoder.repository.DealerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final DealerRepository dealerRepository;

    @PostConstruct
    public void init() {
        if (dealerRepository.count() == 0) { // only insert if empty
            dealerRepository.save(Dealer.builder()
                    .name("John Traders")
                    .address("123 Market Street, Springfield")
                    .contactNumber("9876543210")
                    .build());

            dealerRepository.save(Dealer.builder()
                    .name("Sunrise Auto Parts")
                    .address("45 Industrial Area, Dallas")
                    .contactNumber("9123456780")
                    .build());

            dealerRepository.save(Dealer.builder()
                    .name("Elite Motors")
                    .address("78 Downtown Blvd, Chicago")
                    .contactNumber("9988776655")
                    .build());

            System.out.println("✅ Sample dealer data inserted successfully!");
        }
    }
}
