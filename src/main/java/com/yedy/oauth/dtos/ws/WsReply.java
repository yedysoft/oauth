package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsReply implements Serializable {
    String title;
    String value;
    Object messageId;
}
