package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.RoomConfig;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface RoomConfigDao extends BaseRepository<RoomConfig> {
    RoomConfig findByDeletedFalseAndRoom_Id(UUID id);
}
