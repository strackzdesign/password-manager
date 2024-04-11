package com.passwordmanager.back.features.passwords;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public PasswordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PasswordDTO entityToDto(Password entity) {
        return modelMapper.map(entity, PasswordDTO.class);
    }

    public Password dtoToEntity(PasswordDTO dto) {
        return modelMapper.map(dto, Password.class);
    }
}
