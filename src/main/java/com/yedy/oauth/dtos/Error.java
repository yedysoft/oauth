package com.yedy.oauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Error implements Serializable {
    Integer code;
    String message;
}
