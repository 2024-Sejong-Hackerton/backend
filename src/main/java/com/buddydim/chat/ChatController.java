package com.buddydim.chat;

import com.buddydim.chat.dto.ChatRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 채팅 저장
    @PostMapping("/save")
    public ResponseEntity<Void> saveChat(@RequestBody ChatRequestDto chatRequestDto) {
        chatService.saveContent(chatRequestDto);
        return ResponseEntity.ok().build();
    }

    // 채팅 기록 조회
    @GetMapping("/history")
    public ResponseEntity<List<String>> getChatHistory() {
        List<String> chatHistory = chatService.getChatHistory();
        return ResponseEntity.ok(chatHistory);
    }
}
