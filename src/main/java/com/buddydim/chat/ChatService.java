package com.buddydim.chat;

import com.buddydim.chat.dto.ChatRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Transactional
    // 채팅 저장
    public void saveContent(ChatRequestDto chatRequestDto) {
        chatRepository.save(chatRequestDto.getContent());
    }

    @Transactional
    // 채팅 기록 조회
    public List<String> getChatHistory() {
        return chatRepository.findAll();
    }
}

