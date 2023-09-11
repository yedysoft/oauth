package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsVotedPlaylist implements Serializable {
    WsTrack track;
    Long voteCount;
}
