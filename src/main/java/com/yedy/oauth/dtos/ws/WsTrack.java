package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsTrack implements Serializable {
    final String id;
    final String uri;
    final String name;
    final WsArtist[] artists;
    final WsAlbum album;
    final Integer durationMs;
}
