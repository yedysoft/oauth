package com.yedy.oauth.services;

import com.yedy.oauth.dtos.YedyToken;

import java.util.UUID;

public interface TokenService {
    String generateToken(UUID id);

    YedyToken validateToken(String token);
}
