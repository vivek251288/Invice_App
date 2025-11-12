package com.javaCoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}