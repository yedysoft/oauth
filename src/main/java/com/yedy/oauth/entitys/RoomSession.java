package com.yedy.oauth.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "room_session")
public class RoomSession extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private User room;
    @Temporal(TemporalType.TIMESTAMP)
    private Date starting;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ending;
    @Column(nullable = false)
    private boolean live;
}
