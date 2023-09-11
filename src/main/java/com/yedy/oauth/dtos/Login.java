package com.yedy.oauth.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {
    String name;
    String pass;
}
