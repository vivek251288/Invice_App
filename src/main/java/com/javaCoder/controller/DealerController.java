//package com.javaCoder.controller;
//
//
////import com.javaCoder.invoicepdf.entity.Dealer;
////import com.javaCoder.invoicepdf.repository.DealerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.javaCoder.model.Dealer;
//import com.javaCoder.repository.DealerRepository;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/dealer")
//@RequiredArgsConstructor
//public class DealerController {
//
//    private final DealerRepository dealerRepository;
//
//    // Add a new dealer
//    @PostMapping("/add")
//    public ResponseEntity<Dealer> addDealer(@RequestBody Dealer dealer) {
//        Dealer saved = dealerRepository.save(dealer);
//        return ResponseEntity.ok(saved);
//    }
//
//    // Get all dealers
//    @GetMapping("/all")
//    public ResponseEntity<List<Dealer>> getAllDealers() {
//        return ResponseEntity.ok(dealerRepository.findAll());
//    }
//}
