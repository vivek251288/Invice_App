package com.javaCoder.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.Dealer;


public interface DealerRepository extends JpaRepository<Dealer, Long> {}
