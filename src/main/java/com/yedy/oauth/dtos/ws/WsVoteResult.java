package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsVoteResult implements Serializable {
    String musicId;
    Long voteCount;
}
