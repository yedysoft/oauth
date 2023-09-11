package com.yedy.oauth.services.db;

import com.yedy.oauth.entitys.User;

public interface UserService extends BaseService<User> {
    User findByUserName(String userName);
}
