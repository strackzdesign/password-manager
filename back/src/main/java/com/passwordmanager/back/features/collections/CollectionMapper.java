package com.passwordmanager.back.features.collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CollectionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CollectionDTO entityToDto(Collection entity) {
        return modelMapper.map(entity, CollectionDTO.class);
    }

    public Collection dtoToEntity(CollectionDTO dto) {
        return modelMapper.map(dto, Collection.class);
    }
}
