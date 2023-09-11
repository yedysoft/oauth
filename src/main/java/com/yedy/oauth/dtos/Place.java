package com.yedy.oauth.dtos;

import com.yedy.muk.dtos.ws.WsCurrentlyPlaying;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Place implements Serializable {
    UUID id;
    String name;
    String description;
    WsCurrentlyPlaying liveSong;
    Long population;
    Boolean isLive;

    public Place(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
