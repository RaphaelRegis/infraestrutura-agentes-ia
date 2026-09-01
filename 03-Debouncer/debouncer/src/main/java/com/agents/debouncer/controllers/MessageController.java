package com.agents.debouncer.controllers;

import com.agents.debouncer.dto.telegram.ReceivedTelegramMessageDTO;
import com.agents.debouncer.dto.whatsapp.ReceivedWhatsappMessageDTO;
import com.agents.debouncer.services.telegramDebouncer.TelegramDebouncerService;
import com.agents.debouncer.services.whatsappDebouncer.WhatsappDebouncerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class MessageController {

    private final WhatsappDebouncerService whatsappDebouncerService;
    private final TelegramDebouncerService telegramDebouncerService;

    @PostMapping(value = "v1/whatsapp/debounceMessage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> debounceWhatsappMessages(@RequestBody ReceivedWhatsappMessageDTO messageDTO) {
        whatsappDebouncerService.debounceMessages(messageDTO).subscribe();
        return ResponseEntity.accepted().body("\"mensagem_recebida\"");
    }

    @PostMapping(value = "v1/telegram/debounceMessage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> debounceTelegramMessages(@RequestBody ReceivedTelegramMessageDTO messageDTO) {
        telegramDebouncerService.debounceMessages(messageDTO).subscribe();
        return ResponseEntity.accepted().body("\"mensagem_recebida\"");
    }
}
