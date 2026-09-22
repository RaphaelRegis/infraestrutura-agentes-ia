package com.agents.messageSender.controllers;

import com.agents.messageSender.dto.uazapi.UazapiReceivedMediaMessageDTO;
import com.agents.messageSender.dto.uazapi.UazapiReceivedTextMessageDTO;
import com.agents.messageSender.services.uazapi.UazapiService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.ObjectMapper;

@Controller
@AllArgsConstructor
public class UazapiMessageController {

    private final String UAZAPI_TEXT_QUEUE = "test-queue";
    private UazapiService uazapiService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = UAZAPI_TEXT_QUEUE)
    public void receiveUazapiTextMessage(String body) {
        UazapiReceivedTextMessageDTO receivedTextMessageDTO = objectMapper.readValue(body, UazapiReceivedTextMessageDTO.class);
        uazapiService.sendTextMessage(receivedTextMessageDTO);
    }

    // TODO: definir o nome da fila e reativar o @RabbitListener
    public void receiveUazapiMediaMessage(UazapiReceivedMediaMessageDTO receivedMessageDTO) {
        //uazapiService.sendMediaMessage(receivedMessageDTO);
    }

}
