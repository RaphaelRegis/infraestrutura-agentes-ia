package com.messageAdapter.MessageAdapter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/telegram")
@AllArgsConstructor
public class TelegramController {

    public ResponseEntity<String> adaptMessage() {
        return ResponseEntity.accepted().body("mensagem_recebida");
    }
}
