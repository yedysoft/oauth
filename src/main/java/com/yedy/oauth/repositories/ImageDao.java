package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.Image;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface ImageDao extends BaseRepository<Image> {
    Image findByTableNameAndTableIdAndDeletedFalse(String tableName, UUID tableId);
}
