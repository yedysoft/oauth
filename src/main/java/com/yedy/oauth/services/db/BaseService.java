package com.yedy.oauth.services.db;

import com.yedy.muk.entitys.BaseEntity;

import java.util.List;
import java.util.UUID;

public interface BaseService<T extends BaseEntity> {
    List<T> findAll();

    T findById(UUID id);

    T save(T entity, boolean flush);

    void deleteSafe(T entity, boolean flush);

    void deleteAllSafe(List<T> entities, boolean flush);

    void deleteByIdSafe(UUID id, boolean flush);

    void deleteAllByIdSafe(List<UUID> ids, boolean flush);
}
