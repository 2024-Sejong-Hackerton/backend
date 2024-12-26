package com.buddydim.grpc.application;

import com.buddydim.ChatRequest;
import com.buddydim.ChatResponse;
import com.buddydim.ChatServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GrpcClientService {

    private final ManagedChannel channel;
    private final ChatServiceGrpc.ChatServiceStub stub;

    public GrpcClientService() {
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        this.stub = ChatServiceGrpc.newStub(this.channel);
    }

    public Mono<ChatResponse> sendChat(final String chatText) {
        ChatRequest request = ChatRequest.newBuilder()
                                         .setChat(chatText)
                                         .build();

        return Mono.create(sink -> {
            stub.sendChat(request, new StreamObserver<>() {
                @Override
                public void onNext(ChatResponse value) {
                    sink.success(value);
                }

                @Override
                public void onError(Throwable t) {
                    sink.error(t);
                }

                @Override
                public void onCompleted() {
                    channel.shutdown();
                }
            });
        });
    }
}
