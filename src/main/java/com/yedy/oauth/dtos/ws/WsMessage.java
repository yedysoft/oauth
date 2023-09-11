package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WsMessage implements Serializable {
    String _id;
    String text;
    Date createdAt;
    WsUser user;
    String image;
    String video;
    String audio;
    Boolean system;
    Boolean sent;
    Boolean received;
    Boolean pending;
    WsQuickReplies quickReplies;
}

