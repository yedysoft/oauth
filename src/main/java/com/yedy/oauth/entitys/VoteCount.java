package com.yedy.oauth.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "vote_count")
public class VoteCount extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vote_date", nullable = false)
    private Date voteDate;
}