package com.yedy.oauth.servicesimpl;

import com.google.gson.Gson;
import com.yedy.oauth.entitys.User;
import com.yedy.oauth.services.HelperService;
import com.yedy.oauth.services.db.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HelperServiceImpl implements HelperService {
    private final UserRoleService userRoleService;

    @Override
    public Set<GrantedAuthority> getGrantedRoles(User user) {
        Set<GrantedAuthority> grantedAuths = new HashSet<>();
        List<String> roles = userRoleService.getRoles(user.getId());
        for (String r : roles)
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + r));
        return grantedAuths;
    }

    @Override
    public boolean isPassRange(String pass) {
        return pass != null && pass.length() >= 8 && pass.length() <= 36;
    }

    @Override
    public String toJson(Object src) {
        Gson g = new Gson();
        return g.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        Gson g = new Gson();
        return g.fromJson(json, classOfT);
    }
}
