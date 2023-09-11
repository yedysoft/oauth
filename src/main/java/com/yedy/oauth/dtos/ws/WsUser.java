package com.yedy.oauth.dtos.ws;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class WsUser implements Serializable {
    UUID _id;
    String name;
    String avatar;
}
