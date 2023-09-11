package com.yedy.oauth.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vote")
public class Vote extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private RoomSession session;
    @Column(name = "music_id", length = 100, nullable = false)
    private String musicId;
    @Column(nullable = false)
    private boolean played;
}
