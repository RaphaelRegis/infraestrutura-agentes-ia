package com.messageAdapter.MessageAdapter.controllers;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.image.ReceivedTelegramImageMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;
import com.messageAdapter.MessageAdapter.services.telegram.TelegramAdapter;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/telegram")
@AllArgsConstructor
@EnableAsync
public class TelegramController {

    private TelegramAdapter telegramAdapter;

    @Async
    @PostMapping(value = "/text", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<String>> adaptTextMessage(@RequestBody ReceivedTelegramTextMessageDTO textMessageDTO) {

        return CompletableFuture.supplyAsync(() -> {
            telegramAdapter.adaptTextMessage(textMessageDTO);
            return ResponseEntity.accepted().body("\"mensagem_recebida\"");
        });
    }

    @Async
    @PostMapping(value = "/audio", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<String>> adaptAudioMessage(@RequestBody ReceivedTelegramAudioMessageDTO audioMessageDTO) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                telegramAdapter.adaptAudioMessage(audioMessageDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.accepted().body("\"mensagem_recebida\"");
        });
    }

    @Async
    @PostMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<String>> adaptImageMessage(@RequestBody ReceivedTelegramImageMessageDTO imageMessageDTO) {
        return CompletableFuture.supplyAsync(() -> {
            telegramAdapter.adaptImageMessage(imageMessageDTO);
            return ResponseEntity.accepted().body("\"mensagem_recebida\"");
        });
    }
}

