package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class WsVoteModel implements Serializable {
    UUID userId;
    String musicId;
}
