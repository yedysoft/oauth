package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsArtist implements Serializable {
    String id;
    String name;
    String uri;
}
