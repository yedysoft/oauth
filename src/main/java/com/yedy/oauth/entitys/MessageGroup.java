package com.yedy.oauth.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "message_group")
public class MessageGroup extends BaseEntity {
    private String name;
}
