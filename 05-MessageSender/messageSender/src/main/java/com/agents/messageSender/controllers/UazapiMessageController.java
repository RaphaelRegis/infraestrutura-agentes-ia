package com.agents.messageSender.controllers;

import com.agents.messageSender.dto.uazapi.UazapiReceivedMediaMessageDTO;
import com.agents.messageSender.dto.uazapi.UazapiReceivedTextMessageDTO;
import com.agents.messageSender.services.uazapi.UazapiService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UazapiMessageController {

    private final String UAZAPI_TEXT_QUEUE = "";
    private final String UAZAPI_MEDIA_QUEUE = "";
    private UazapiService uazapiService;

    @RabbitListener(queues = UAZAPI_TEXT_QUEUE)
    public void receiveUazapiTextMessage(UazapiReceivedTextMessageDTO receivedTextMessageDTO) {
        uazapiService.sendTextMessage(receivedTextMessageDTO);
    }

    @RabbitListener(queues = UAZAPI_MEDIA_QUEUE)
    public void receiveUazapiMediaMessage(UazapiReceivedMediaMessageDTO receivedMessageDTO) {
        uazapiService.sendMediaMessage(receivedMessageDTO);
    }

}
