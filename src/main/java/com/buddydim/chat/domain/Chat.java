package com.buddydim.chat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ChatStatus chatStatus;

    @Column(nullable = false, length = 1500)
    private String content;

    private Long userId;

    public Chat(final Long id,
                final ChatStatus chatStatus,
                final String content,
                final Long userId) {
        this.id = id;
        this.chatStatus = chatStatus;
        this.content = content;
        this.userId = userId;
    }

    public Chat(final ChatStatus chatStatus,
                final String content,
                final Long userId) {
        this(null, chatStatus, content, userId);
    }

    protected Chat() {
    }

    public Long getId() {
        return id;
    }

    public ChatStatus getChatStatus() {
        return chatStatus;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }
}
