package com.yedy.oauth.services.db;

import com.yedy.oauth.entitys.User;
import com.yedy.oauth.entitys.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserRoleService extends BaseService<UserRole> {
    List<UserRole> findByUser(User user);

    List<String> getRoles(UUID userId);
}
