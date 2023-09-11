package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WsQuickReplies implements Serializable {
    String type;
    List<WsReply> values;
    Boolean keepIt;
}
