package com.agents.messageSender.services.uazapi;

import com.agents.messageSender.dto.uazapi.UazapiReceivedMediaMessageDTO;
import com.agents.messageSender.dto.uazapi.UazapiReceivedTextMessageDTO;
import com.agents.messageSender.services.uazapi.usecases.SendUazapiMediaMessageUsecase;
import com.agents.messageSender.services.uazapi.usecases.SendUazapiTextMessageUsecase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UazapiServiceImpl implements UazapiService {

    private SendUazapiTextMessageUsecase sendUazapiTextMessageUsecase;
    private SendUazapiMediaMessageUsecase sendUazapiMediaMessageUsecase;

    @Override
    public void sendTextMessage(UazapiReceivedTextMessageDTO receivedMessageDTO) {
        sendUazapiTextMessageUsecase.sendUazapiTextMessage();
    }

    @Override
    public void sendMediaMessage(UazapiReceivedMediaMessageDTO receivedMessageDTO) {
        sendUazapiMediaMessageUsecase.sendUazapiMediaMessageUsecase();
    }
}
