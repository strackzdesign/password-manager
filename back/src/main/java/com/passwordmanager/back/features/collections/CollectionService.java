package com.passwordmanager.back.features.collections;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public interface CollectionService {
    CollectionDTO findById(@NotBlank UUID id);

    CollectionDTO save(@NotBlank CollectionDTO dto);

    void delete(@NotBlank UUID id);
}
