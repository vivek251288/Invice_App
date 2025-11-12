package com.javaCoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.UserPreference;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    List<UserPreference> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}