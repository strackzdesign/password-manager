package com.passwordmanager.back.features.passwords;

import com.passwordmanager.back.exceptions.NotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordServiceImpl implements PasswordService {
    public final PasswordRepository passwordRepository;
    public final PasswordMapper passwordMapper;

    @Autowired
    public PasswordServiceImpl(PasswordRepository passwordRepository, PasswordMapper passwordMapper) {
        this.passwordRepository = passwordRepository;
        this.passwordMapper = passwordMapper;
    }

    @Override
    public PasswordDTO findById(UUID id) {
        Password entity = getEntityOrThrow(id);
        return passwordMapper.entityToDto(entity);
    }

    @Override
    @Transactional
    public PasswordDTO save(PasswordDTO dto) {
        if(Objects.nonNull(dto.getId())) {
            // UPDATE ENTITY
            return passwordMapper.entityToDto(updateEntity(dto));
        } else {
            // CREATE ENTITY
            return passwordMapper.entityToDto(createEntity(dto));
        }
    }

    @Override
    @Transactional
    public void delete(@NotBlank UUID id) {
        Password entity = getEntityOrThrow(id);
        try {
            passwordRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error occurred while deleting the entity", e);
        }
    }

    private Password getEntityOrThrow(UUID id) {
        return passwordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity with id " + id + " not found"));
    }

    private Password createEntity(PasswordDTO dto) {
        Password toSaveEntity = Password
                .builder()
                .password(Base64.getEncoder().encodeToString(dto.getPassword().getBytes()))
                .description(dto.getDescription())
                .build();

        return passwordRepository.save(toSaveEntity);
    }

    private Password updateEntity(PasswordDTO dto) throws NotFoundException {
        Optional<Password> entity = passwordRepository.findById(UUID.fromString(dto.getId()));

        if(entity.isPresent()) {
            entity.get().setPassword(Base64.getEncoder().encodeToString(dto.getPassword().getBytes()));
            entity.get().setDescription(dto.getDescription());
            return passwordRepository.save(entity.get());
        }

        throw new NotFoundException("Entity with id " + dto.getId() + " not found");
    }
}
