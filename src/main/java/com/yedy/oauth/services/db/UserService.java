package com.yedy.oauth.services.db;

import com.yedy.muk.dtos.PassChange;
import com.yedy.muk.dtos.Place;
import com.yedy.muk.entitys.User;
import com.yedy.muk.enums.Roles;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByUserName(String userName);

    void passChange(PassChange pc);

    List<Place> getPlaces(Roles role);
}
