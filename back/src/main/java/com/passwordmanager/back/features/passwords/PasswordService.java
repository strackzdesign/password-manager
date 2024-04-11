package com.passwordmanager.back.features.passwords;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public interface PasswordService {
    PasswordDTO findById(@NotBlank UUID id);

    PasswordDTO save(@NotBlank PasswordDTO dto);

    void delete(@NotBlank UUID id);
}
