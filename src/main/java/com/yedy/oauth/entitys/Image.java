package com.yedy.oauth.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "image")
public class Image extends BaseEntity {
    @Column(name = "table_name", length = 30, nullable = false)
    private String tableName;
    @Column(name = "table_id", nullable = false)
    private UUID tableId;
    @Column(nullable = false)
    private String path;
}