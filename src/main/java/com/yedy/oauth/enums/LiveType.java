package com.yedy.oauth.enums;

public enum LiveType {
    RoomAdmin("/room/admin"), RoomUser("/room/user"), PrivateChat("/chat/private"), UserLive("/user");

    public final String value;

    LiveType(String room) {
        value = room;
    }
}
