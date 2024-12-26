package com.buddydim.chat.presentation;

import com.buddydim.chat.application.ChatService;
import com.buddydim.chat.presentation.dto.PostChatRequest;
import com.buddydim.jwt.JwtUtil;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final JwtUtil jwtUtil;

    public ChatController(final ChatService chatService,
                          final JwtUtil jwtUtil) {
        this.chatService = chatService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/chat")
    public ResponseEntity<Void> createChat(@RequestBody PostChatRequest request,
                                           @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.getUserIdFromToken(token);
        final Long chatId = chatService.createChat(request, userId);
        return ResponseEntity.status(HttpStatus.OK)
                             .location(URI.create("/chat/status/" + chatId))
                             .build();
    }
}
