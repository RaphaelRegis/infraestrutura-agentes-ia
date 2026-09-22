package com.agents.debouncer.controllers;

import com.agents.debouncer.dto.telegram.ReceivedTelegramMessageDTO;
import com.agents.debouncer.dto.uazapi.ReceivedUazapiMessageDTO;
import com.agents.debouncer.dto.whatsapp.ReceivedWhatsappMessageDTO;
import com.agents.debouncer.services.telegramDebouncer.TelegramDebouncerService;
import com.agents.debouncer.services.uazapiDebouncer.UazapiDebouncerService;
import com.agents.debouncer.services.whatsappDebouncer.WhatsappDebouncerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.ObjectMapper;

@Controller
@AllArgsConstructor
public class MessageController {

    private final String WHATSAPP_MESSAGE = "debouncer-whatsapp";
    private final String TELEGRAM_MESSAGE = "debouncer-telegram";
    private final String UAZAPI_MESSAGE = "debouncer-uazapi";
    private final ObjectMapper objectMapper;
    private final WhatsappDebouncerService whatsappDebouncerService;
    private final TelegramDebouncerService telegramDebouncerService;
    private final UazapiDebouncerService uazapiDebouncerService;

    @RabbitListener(queues = WHATSAPP_MESSAGE)
    public void debounceWhatsappMessages(String body) {
        ReceivedWhatsappMessageDTO messageDTO = objectMapper.readValue(body, ReceivedWhatsappMessageDTO.class);
        //whatsappDebouncerService.debounceMessages(messageDTO).subscribe();
    }

    @RabbitListener(queues = TELEGRAM_MESSAGE)
    public void debounceTelegramMessages(String body) {
        ReceivedTelegramMessageDTO messageDTO = objectMapper.readValue(body, ReceivedTelegramMessageDTO.class);
        System.out.println(messageDTO);
        telegramDebouncerService.debounceMessages(messageDTO).subscribe();
    }

    @RabbitListener(queues = UAZAPI_MESSAGE)
    public void debounceUazapiMessages(String body) {
        ReceivedUazapiMessageDTO messageDTO = objectMapper.readValue(body, ReceivedUazapiMessageDTO.class);
        uazapiDebouncerService.debounceMessages(messageDTO).subscribe();
    }
}
