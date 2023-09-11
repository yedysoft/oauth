package com.yedy.oauth.entitys;

import com.yedy.oauth.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "s_user")
public class User extends BaseEntity {
    @Column(name = "user_name", length = 50, nullable = false)
    private String userName;
    @Column(name = "user_pass", length = 500, nullable = false)
    private String userPass;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100)
    private String name;
    @Column(length = 100)
    private String surname;
    @Column(name = "tel_number", length = 15)
    private String telNumber;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Enumerated
    @Column
    private Gender gender;
    @Column
    private Long coin;

    public User(UUID id) {
        this.setId(id);
    }
}