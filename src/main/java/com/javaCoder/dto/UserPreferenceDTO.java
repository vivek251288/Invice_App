package com.javaCoder.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPreferenceDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Preference IDs cannot be empty")
    private List<Long> preferenceIds;

    // Getters and setters
}