package com.yedy.oauth.servicesimpl.db;

import com.yedy.muk.entitys.BaseEntity;
import com.yedy.muk.repositories.BaseRepository;
import com.yedy.muk.services.db.BaseService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    private final BaseRepository<T> repo;

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

    @Override
    public T findById(UUID id) {
        return repo.findByIdAndDeletedFalse(id);
    }

    @Override
    public T save(T entity, boolean flush) {
        T out = repo.save(entity);
        if (flush)
            repo.flush();
        return out;
    }

    @Override
    public void deleteSafe(T entity, boolean flush) {
        entity.setDeleted(true);
        repo.save(entity);
        if (flush)
            repo.flush();
    }

    @Override
    public void deleteAllSafe(List<T> entities, boolean flush) {
        for (T e : entities)
            deleteSafe(e, flush);
    }

    @Override
    public void deleteByIdSafe(UUID id, boolean flush) {
        deleteAllByIdSafe(List.of(id), flush);
    }

    @Override
    public void deleteAllByIdSafe(List<UUID> ids, boolean flush) {
        List<T> entities = repo.findAllById(ids);
        deleteAllSafe(entities, flush);
    }
}
