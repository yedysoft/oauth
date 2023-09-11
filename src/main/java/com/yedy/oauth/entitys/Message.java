package com.yedy.oauth.entitys;

import com.yedy.muk.enums.MessageContentType;
import com.yedy.muk.enums.MessageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private MessageGroup group;
    @ManyToOne
    @JoinColumn(name = "session_id")
    private RoomSession session;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JdbcTypeCode(SqlTypes.CLOB)
    @Column(nullable = false)
    private String content;
    @Enumerated
    @Column(nullable = false)
    private MessageType type;
    @Enumerated
    @Column(name = "content_type", nullable = false)
    private MessageContentType contentType;
    @ManyToOne
    @JoinColumn(name = "quoted_message_id")
    private Message quotedMessage;
}