package com.passwordmanager.back.features.passwords;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/passwords")
public class PasswordController {
    PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PasswordDTO> getById(@NotBlank @PathVariable(name = "id") String id) {
        PasswordDTO returnValue = passwordService.findById(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @PutMapping
    public ResponseEntity<PasswordDTO> savePassword(@Valid @RequestBody PasswordDTO passwordDTO) {
        PasswordDTO savedPassword = passwordService.save(passwordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPassword);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> savePassword(@NotBlank @PathVariable(name = "id") String id) {
        passwordService.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
