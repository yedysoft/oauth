package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.Auths;
import com.yedy.muk.enums.AuthsType;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface AuthsDao extends BaseRepository<Auths> {
    Auths findByDeletedFalseAndTypeAndUser_Id(AuthsType type, UUID id);
}
