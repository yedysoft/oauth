package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.Vote;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface VoteDao extends BaseRepository<Vote> {
    Vote findByDeletedFalseAndPlayedFalseAndMusicIdAndSession_Id(String musicId, UUID session_id);

    List<Vote> findByDeletedFalseAndPlayedFalseAndSession_Id(UUID id);
}