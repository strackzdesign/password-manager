package com.passwordmanager.back.features.collections;

import com.passwordmanager.back.features.passwords.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDTO {
    private UUID id;

    private String title;

    private String description;

    private String hexColor;

    private Set<Password> passwords;

}
