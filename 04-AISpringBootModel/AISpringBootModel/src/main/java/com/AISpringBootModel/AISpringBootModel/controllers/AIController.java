package com.AISpringBootModel.AISpringBootModel.controllers;

import com.AISpringBootModel.AISpringBootModel.services.telegram.AITelegram;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/ai")
@AllArgsConstructor
@EnableAsync
public class AIController {

    private AITelegram aiTelegram;

    @Async
    @PostMapping(value = "v1/whatsapp/answer")
    public CompletableFuture<ResponseEntity<String>> answerWhatsapp() {
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.accepted().body("\"mensagem_recebida\"");
        });
    }

    @Async
    @PostMapping(value = "v1/telegram/answer")
    public CompletableFuture<ResponseEntity<String>> answerTelegram() {
        return CompletableFuture.supplyAsync(() -> {
            aiTelegram.answer();
            return ResponseEntity.accepted().body("\"mensagem_recebida\"");
        });
    }

}
