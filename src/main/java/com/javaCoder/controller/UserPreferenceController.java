package com.javaCoder.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaCoder.dto.PreferenceDTO;
import com.javaCoder.dto.UserPreferenceDTO;
import com.javaCoder.service.UserPreferenceService;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

    private final UserPreferenceService service;

    public UserPreferenceController(UserPreferenceService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePreferences(@Valid @RequestBody UserPreferenceDTO dto) {
        service.saveUserPreferences(dto);
        return ResponseEntity.ok("User preferences updated successfully.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PreferenceDTO>> getUserPreferences(@PathVariable Long userId) {
        List<PreferenceDTO> prefs = service.getUserPreferences(userId);
        return ResponseEntity.ok(prefs);
    }
}