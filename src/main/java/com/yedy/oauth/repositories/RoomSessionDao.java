package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.RoomSession;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface RoomSessionDao extends BaseRepository<RoomSession> {
    List<RoomSession> findByDeletedFalseAndRoom_DeletedFalseAndRoom_Id(UUID id);
}
