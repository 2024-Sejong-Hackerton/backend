package com.buddydim.chat.presentation;

//import com.buddydim.chat.application.ChatService;
import com.buddydim.chat.presentation.dto.PostChatRequest;
import com.buddydim.jwt.JwtUtil;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
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
    public ResponseEntity<Void> createChat(@RequestBody final PostChatRequest request,
                                           @RequestHeader("Authorization") final String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.getUserIdFromToken(token);
        final Long chatId = chatService.createChat(request, userId);
        return ResponseEntity.status(HttpStatus.OK)
                             .location(URI.create("/chat/status/" + chatId))
                             .build();
    }

    @GetMapping("/chat/status/{chatId}")
    public ResponseEntity<String> getChatStatus(@PathVariable final Long chatId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(chatService.getChatStatus(chatId));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<String> getChat(@PathVariable final Long chatId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(chatService.getChat(chatId));
    }
}
=======
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
>>>>>>> 980ea15 (feat : 엑셀로 시간표 뽑기)
