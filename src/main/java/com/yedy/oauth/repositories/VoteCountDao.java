package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.Vote;
import com.yedy.muk.entitys.VoteCount;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VoteCountDao extends BaseRepository<VoteCount> {
    long countByVoteAndDeletedFalse(Vote vote);
}
