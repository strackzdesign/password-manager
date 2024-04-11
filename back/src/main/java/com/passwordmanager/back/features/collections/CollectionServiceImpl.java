package com.passwordmanager.back.features.collections;

import com.passwordmanager.back.exceptions.NotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CollectionServiceImpl implements CollectionService {
    public final CollectionRepository collectionRepository;
    public final CollectionMapper collectionMapper;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionMapper collectionMapper) {
        this.collectionRepository = collectionRepository;
        this.collectionMapper = collectionMapper;
    }

    @Override
    public CollectionDTO findById(UUID id) {
        Collection entity = getEntityOrThrow(id);
        return collectionMapper.entityToDto(entity);
    }

    @Override
    @Transactional
    public CollectionDTO save(CollectionDTO dto) {
        if(Objects.nonNull(dto.getId())) {
            // UPDATE ENTITY
            return collectionMapper.entityToDto(updateEntity(dto));
        } else {
            // CREATE ENTITY
            return collectionMapper.entityToDto(createEntity(dto));
        }
    }

    @Override
    @Transactional
    public void delete(@NotBlank UUID id) {
        Collection entity = getEntityOrThrow(id);
        try {
            collectionRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new PersistenceException("Error occurred while deleting the entity", e);
        }
    }

    private Collection getEntityOrThrow(UUID id) {
        return collectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity with id " + id + " not found"));
    }

    private Collection createEntity(CollectionDTO dto) {
        Collection toSaveEntity = Collection
                .builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .hexColor(dto.getHexColor())
                .build();

        return collectionRepository.save(toSaveEntity);
    }

    private Collection updateEntity(CollectionDTO dto) throws NotFoundException {
        Optional<Collection> entity = collectionRepository.findById(dto.getId());

        if(entity.isPresent()) {
            entity.get().setTitle(dto.getTitle());
            entity.get().setDescription(dto.getDescription());
            entity.get().setHexColor(dto.getHexColor());
            return collectionRepository.save(entity.get());
        }

        throw new NotFoundException("Entity with id " + dto.getId() + " not found");
    }
}
