package com.yedy.oauth.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PassChange implements Serializable {
    UUID id;
    String oldPass;
    String newPass;
}
