package com.messageAdapter.MessageAdapter.controllers;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/telegram")
@AllArgsConstructor
public class TelegramController {

    @PostMapping("/text")
    public ResponseEntity<String> adaptTextMessage(@RequestBody ReceivedTelegramTextMessageDTO textMessageDTO) {
        return ResponseEntity.accepted().body("mensagem_recebida");
    }

    @PostMapping("/audio")
    public ResponseEntity<String> adaptAudioMessage(@RequestBody ReceivedTelegramAudioMessageDTO audioMessageDTO) {
        return ResponseEntity.accepted().body("mensagem_recebida");
    }
}
