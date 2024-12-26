package com.buddydim.application;

import com.buddydim.ChatRequest;
import com.buddydim.ChatResponse;
import com.buddydim.ChatServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    public String sendMessage(String chatText) {
        // gRPC 서버(예: Python gRPC) 주소 & 포트. 여기서는 localhost:50051 가정
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()  // 테스트 용도로 TLS 미사용
                .build();

        // Blocking Stub 생성
        ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);

        // 요청 생성
        ChatRequest request = ChatRequest.newBuilder()
                .setChat(chatText)
                .build();

        // gRPC 호출
        ChatResponse response = stub.sendChat(request);

        // 채널 닫기
        channel.shutdown();

        // 결과 문자열 반환
        return response.getMessage();
    }
}
