package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.Message;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MessageDao extends BaseRepository<Message> {
}