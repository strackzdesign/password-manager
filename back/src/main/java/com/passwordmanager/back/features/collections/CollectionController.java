package com.passwordmanager.back.features.collections;

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
@RequestMapping("/collections")
public class CollectionController {
    CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionDTO> getById(@NotBlank @PathVariable(name = "id") String id) {
        CollectionDTO returnValue = collectionService.findById(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @PutMapping
    public ResponseEntity<CollectionDTO> save(@Valid @RequestBody CollectionDTO passwordDTO) {
        CollectionDTO savedPassword = collectionService.save(passwordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPassword);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@NotBlank @PathVariable(name = "id") String id) {
        collectionService.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
