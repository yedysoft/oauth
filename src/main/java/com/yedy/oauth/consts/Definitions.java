package com.yedy.oauth.consts;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Definitions {
    public static final UUID emptyId = UUID.nameUUIDFromBytes("empty".getBytes(StandardCharsets.UTF_8));
    public static final Long votePrice = 100L;
}
