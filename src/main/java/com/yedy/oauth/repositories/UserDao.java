package com.yedy.oauth.repositories;

import com.yedy.oauth.entitys.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserDao extends BaseRepository<User> {
    User findByUserNameAndDeletedFalse(String userName);
}
