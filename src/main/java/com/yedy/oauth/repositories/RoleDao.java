package com.yedy.oauth.repositories;

import com.yedy.muk.entitys.Role;
import com.yedy.muk.enums.Roles;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleDao extends BaseRepository<Role> {
    Role findByCodeAndDeletedFalse(Roles code);
}