package com.buddydim.chat.application;

import com.buddydim.chat.domain.Chat;
import com.buddydim.chat.domain.ChatRepository;
import com.buddydim.chat.domain.ChatStatus;
import com.buddydim.chat.presentation.dto.PostChatRequest;
import com.buddydim.grpc.application.GrpcClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
@Service
public class ChatService {

    private final GrpcClientService grpcClientService;
    private final ChatRepository chatRepository;

    public ChatService(final GrpcClientService grpcClientService,
                       final ChatRepository chatRepository) {
        this.grpcClientService = grpcClientService;
        this.chatRepository = chatRepository;
    }

    @Transactional
    public Long createChat(final PostChatRequest request,
                           final Long userId) {
        final Chat chat = new Chat(ChatStatus.IN_PROGRESS, "", userId);
        chatRepository.save(chat);

        grpcClientService
                .sendChat(request.content())
                .subscribe(response -> chatRepository.updateFeedbackStatusAndContentById(chat.getId(),
                                                                                         ChatStatus.COMPLETED,
                                                                                         response.getMessage()));

        return chat.getId();
    }

    @Transactional(readOnly = true)
    public String getChatStatus(final Long chatId) {
        return chatRepository.findById(chatId)
                             .map(Chat::getChatStatus)
                             .orElseThrow(() -> new IllegalArgumentException("Chat not found"))
                             .name();
    }

    @Transactional(readOnly = true)
    public String getChat(final Long chatId) {
        return chatRepository.findById(chatId)
                             .map(Chat::getContent)
                             .orElseThrow(() -> new IllegalArgumentException("Chat not found"));
    }
}
=======
//@Service
//public class ChatService {
//
//    private final ChatRepository chatRepository;
//
//    @Autowired
//    public ChatService(ChatRepository chatRepository) {
//        this.chatRepository = chatRepository;
//    }
//
//    @Transactional
//    public void saveContent(final PostChatRequest request, final Long userId) {
//        final Chat chat = new Chat(ChatStatus.IN_PROGRESS, "", userId);
//        chatRepository.save(chat);
//
//        final Mono<FeedbackCreateResponse> mono = feedbackChatCompletionService.completeChat(
//                request);
//        mono.subscribe(
//                response -> feedbackRepository.updateFeedbackStatusAndContentById(feedback.getId(),
//                                                                                  FeedbackStatus.DONE,
//                                                                                  response.feedback()));
//        return feedback.getId();
//    }
//
//    @Transactional
//    public List<String> getChatHistory() {
//        return chatRepository.findAll();
//    }
//}
//
>>>>>>> 980ea15 (feat : 엑셀로 시간표 뽑기)
