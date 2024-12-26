package com.buddydim.chat.dto;

public class ChatRequestDto {
    private String content;

    public ChatRequestDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
