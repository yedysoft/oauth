package com.yedy.oauth.consts;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCrypto {
    private static PasswordEncoder encoder = null;

    public static PasswordEncoder getEncoder() {
        if (encoder == null)
            encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
