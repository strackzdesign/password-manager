package com.passwordmanager.back.features.passwords;

import com.passwordmanager.back.features.collections.CollectionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {
    private UUID id;

    private String password;

    private String description;

    private CollectionDTO collection;
}
