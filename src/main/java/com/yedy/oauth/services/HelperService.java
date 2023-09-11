package com.yedy.oauth.services;

import com.yedy.oauth.entitys.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface HelperService {
    Set<GrantedAuthority> getGrantedRoles(User user);

    boolean isPassRange(String pass);

    String toJson(Object src);

    <T> T fromJson(String json, Class<T> classOfT);
}
