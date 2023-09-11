package com.yedy.oauth.dtos.ws;

import lombok.Data;
import se.michaelthelin.spotify.model_objects.specification.Image;

import java.io.Serializable;

@Data
public class WsAlbum implements Serializable {
    Image[] images;
}
