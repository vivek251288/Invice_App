package com.javaCoder.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.javaCoder.dto.PreferenceDTO;
import com.javaCoder.model.Preference;

@Mapper(componentModel = "spring")
public interface PreferenceMapper {
    PreferenceDTO toDTO(Preference preference);

    List<PreferenceDTO> toDTOList(List<Preference> preferences);
}