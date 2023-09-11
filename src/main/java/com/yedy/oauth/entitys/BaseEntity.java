package com.yedy.oauth.entitys;

import com.yedy.oauth.consts.UserContext;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private boolean deleted;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private UUID createdBy;
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private UUID updatedBy;

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date();
        updatedBy = createdBy = UserContext.getId();
        deleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        updatedBy = UserContext.getId();
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BaseEntity other)) return false;
        return (this.id == null && other.id == null) || (this.id != null && other.id != null && this.id.equals(other.id));
    }
}