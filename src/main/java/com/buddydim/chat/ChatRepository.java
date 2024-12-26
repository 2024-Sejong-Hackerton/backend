package com.buddydim.chat;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatRepository {
    private List<String> chatHistory = new ArrayList<>();

    // 채팅 저장
    public void save(String content) {
        chatHistory.add(content);
    }

    // 채팅 기록 가져오기
    public List<String> findAll() {
        return new ArrayList<>(chatHistory); // 복사본 반환
    }
}

