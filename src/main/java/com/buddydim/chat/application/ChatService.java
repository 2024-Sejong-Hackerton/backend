package com.buddydim.chat.application;

import com.buddydim.chat.domain.Chat;
import com.buddydim.chat.domain.ChatRepository;
import com.buddydim.chat.domain.ChatStatus;
import com.buddydim.chat.presentation.dto.PostChatRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
