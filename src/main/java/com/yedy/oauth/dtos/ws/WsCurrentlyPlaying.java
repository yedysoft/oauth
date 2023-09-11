package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsCurrentlyPlaying implements Serializable {
    Integer progress_ms;
    Boolean is_playing;
    WsTrack item;
}
