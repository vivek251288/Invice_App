package com.javaCoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaCoder.model.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
}
