package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.MessageGroup;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageGroupDao extends BaseRepository<MessageGroup> {
}
