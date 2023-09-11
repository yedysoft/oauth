package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
    T findByIdAndDeletedFalse(UUID id);
}