package com.yedy.oauth.entitys;

import com.yedy.oauth.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "s_role")
public class Role extends BaseEntity {
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "code", length = 50, nullable = false)
    private Roles code;
    @Column(name = "description")
    private String description;
}