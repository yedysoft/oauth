package com.yedy.oauth.exception;

import com.yedy.oauth.consts.ErrorMessages;
import com.yedy.oauth.entitys.BaseEntity;

public abstract class Assert {
    public static void notNull(Object object, String message) {
        if (object != null)
            throw new YedyException(message);
    }

    public static void isNull(Object object, String message) {
        if (object == null)
            throw new YedyException(message);
    }

    public static void isIdNull(BaseEntity entity, String message) {
        if (entity == null || entity.getId() == null)
            throw new YedyException(message);
    }

    public static void isIdNull(BaseEntity entity) {
        isIdNull(entity, ErrorMessages.ID_NULL);
    }

    public static void isTrue(boolean expression, String message) {
        if (expression)
            throw new YedyException(message);
    }

    public static void isFalse(boolean expression, String message) {
        if (!expression)
            throw new YedyException(message);
    }

    public static void isEmpty(String text, String message) {
        if (text == null || text.isEmpty())
            throw new YedyException(message);
    }
}
