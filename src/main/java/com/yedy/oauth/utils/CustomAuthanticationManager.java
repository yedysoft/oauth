package com.yedy.oauth.utils;

import com.yedy.oauth.consts.ErrorMessages;
import com.yedy.oauth.dtos.YedyToken;
import com.yedy.oauth.entitys.User;
import com.yedy.oauth.services.HelperService;
import com.yedy.oauth.services.TokenService;
import com.yedy.oauth.services.db.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomAuthanticationManager implements AuthenticationManager {
    private final UserService userService;
    private final HelperService helperService;
    private final TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof BearerTokenAuthenticationToken bearer) {
            YedyToken t = tokenService.validateToken(bearer.getToken());
            User user = userService.findById(t.getUserId());
            Assert.isNull(user, ErrorMessages.USER_NOT_FOUND);
            Set<GrantedAuthority> gRoles = helperService.getGrantedRoles(user);
            return new UsernamePasswordAuthenticationToken(user.getId(), null, gRoles);
        }
        return null;
    }
}
