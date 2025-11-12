package com.javaCoder.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.javaCoder.dto.PreferenceDTO;
import com.javaCoder.dto.UserPreferenceDTO;
import com.javaCoder.mapper.PreferenceMapper;
import com.javaCoder.model.Preference;
import com.javaCoder.model.User;
import com.javaCoder.model.UserPreference;
import com.javaCoder.repository.PreferenceRepository;
import com.javaCoder.repository.UserPreferenceRepository;
import com.javaCoder.repository.UserRepository;

import java.util.*;

@Service
public class UserPreferenceService {

    private final UserRepository userRepository;
    private final PreferenceRepository preferenceRepository;
    private final UserPreferenceRepository userPreferenceRepository;
    private final PreferenceMapper preferenceMapper;

    public UserPreferenceService(UserRepository userRepository,
            PreferenceRepository preferenceRepository,
            UserPreferenceRepository userPreferenceRepository,
            PreferenceMapper preferenceMapper) {
        this.userRepository = userRepository;
        this.preferenceRepository = preferenceRepository;
        this.userPreferenceRepository = userPreferenceRepository;
        this.preferenceMapper = preferenceMapper;
    }

    @Transactional
    public void saveUserPreferences(UserPreferenceDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Clear old preferences
        userPreferenceRepository.deleteByUserId(user.getId());

        List<Preference> preferences = preferenceRepository.findAllById(dto.getPreferenceIds());
        List<UserPreference> userPreferences = new ArrayList<>();

        for (Preference pref : preferences) {
            UserPreference up = new UserPreference();
            up.setUser(user);
            up.setPreference(pref);
            userPreferences.add(up);
        }

        userPreferenceRepository.saveAll(userPreferences);
    }

    public List<PreferenceDTO> getUserPreferences(Long userId) {
        List<UserPreference> list = userPreferenceRepository.findByUserId(userId);
        List<Preference> preferences = list.stream()
                .map(UserPreference::getPreference)
                .toList();
        return preferenceMapper.toDTOList(preferences);
    }
}