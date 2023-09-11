package com.yedy.oauth.services.db;

import com.yedy.oauth.entitys.Role;
import com.yedy.oauth.enums.Roles;

public interface RoleService extends BaseService<Role> {
    Role findByCode(Roles code);
}
