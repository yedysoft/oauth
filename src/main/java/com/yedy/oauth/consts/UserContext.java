package com.yedy.oauth.consts;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class UserContext {
    public static UUID getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UUID principal)
            return principal;
        return Definitions.emptyId;
    }
}
