package com.buddydim.chat.presentation;

//import com.buddydim.chat.application.ChatService;
import com.buddydim.chat.presentation.dto.PostChatRequest;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class ChatController {
//    private final ChatService chatService;
//
//    public ChatController(ChatService chatService) {
//        this.chatService = chatService;
//    }
//
//    @PostMapping("/chat")
//    public ResponseEntity<Void> postChat(@RequestBody PostChatRequest request) {
//        chatService.saveContent(request);
//        return ResponseEntity.ok().build();
//    }
//
//    // 채팅 기록 조회
//    @GetMapping("/history")
//    public ResponseEntity<List<String>> getChatHistory() {
//        List<String> chatHistory = chatService.getChatHistory();
//        return ResponseEntity.ok(chatHistory);
//    }
//}
