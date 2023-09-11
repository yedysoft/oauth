package com.yedy.oauth.dtos;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class YedyToken {
    UUID userId;
    Date expiration;
}
