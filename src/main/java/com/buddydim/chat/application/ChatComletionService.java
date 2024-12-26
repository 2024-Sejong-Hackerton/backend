package com.buddydim.chat.application;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//public class ChatComletionService {
//
//    private final WebClient webClient;
//
//    public ChatComletionService(final WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    @Override
//    public Mono<FeedbackCreateResponse> completeChat(final FeedbackCreateRequest request) {
//        final Mono<ChatCompletionCreateResponse> mono = webClient.post()
//                                                                 .accept(MediaType.APPLICATION_JSON)
//                                                                 .bodyValue(
//                                                                         ChatCompletionCreateRequest.from(
//                                                                                 request.diaryContent()))
//                                                                 .retrieve()
//                                                                 .bodyToMono(
//                                                                         ChatCompletionCreateResponse.class);
//
//        return mono.flatMap(response -> {
//            final String feedback = response.getMessageContent();
//            return Mono.just(new FeedbackCreateResponse(feedback));
//        });
//    }
//}
