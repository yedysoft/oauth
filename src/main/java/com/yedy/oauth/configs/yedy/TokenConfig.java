package com.yedy.oauth.configs.yedy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@Configuration
@ConfigurationProperties("yedy.token")
public class TokenConfig {
    String algorithm;
    String secretKey;
    ExpirationProperties adminExpiration;
    ExpirationProperties userExpiration;

    @Data
    public static class ExpirationProperties {
        Long time;
        ChronoUnit type;

        public Instant getExpiration() {
            return Instant.now().plus(time, type);
        }
    }
}
