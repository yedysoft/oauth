package com.yedy.oauth.repositories;

import com.yedy.oauth.entitys.Role;
import com.yedy.oauth.enums.Roles;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleDao extends BaseRepository<Role> {
    Role findByCodeAndDeletedFalse(Roles code);
}