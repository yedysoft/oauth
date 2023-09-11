package com.yedy.oauth.entitys;

import com.yedy.muk.enums.AuthsType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "auth")
public class Auths extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "expires_in")
    private Integer expiresIn;
    @Column(name = "token_type")
    private String tokenType;
    private String scope;
    @Enumerated
    @Column(nullable = false)
    private AuthsType type;
}