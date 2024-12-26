package com.buddydim.presentation;

import com.buddydim.application.GrpcClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GrpcController {

    private final GrpcClientService clientService;

    public GrpcController(GrpcClientService clientService) {
        this.clientService = clientService;
    }

    // 예: GET /chat?msg=HelloWorld
    @GetMapping("/chat")
    public String chat(@RequestParam String msg) {
        // gRPC 클라이언트로 메시지 전송
        String response = clientService.sendMessage(msg);
        System.out.println(response);
        return "Server responded: " + response;
    }
}
