package com.yedy.oauth.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "s_user_role")
public class UserRole extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;
    @ManyToOne
    @JoinColumn(name = "role_id", updatable = false, nullable = false)
    public Role role;
}