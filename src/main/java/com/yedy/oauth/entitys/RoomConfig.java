package com.yedy.oauth.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "room_config")
public class RoomConfig extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "room_id", nullable = false)
    private User room;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 300)
    private String description;
}