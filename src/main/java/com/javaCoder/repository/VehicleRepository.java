package com.javaCoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {}
