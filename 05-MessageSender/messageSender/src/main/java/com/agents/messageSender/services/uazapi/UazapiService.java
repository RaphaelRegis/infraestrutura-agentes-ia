package com.agents.messageSender.services.uazapi;

import com.agents.messageSender.dto.uazapi.UazapiReceivedMediaMessageDTO;
import com.agents.messageSender.dto.uazapi.UazapiReceivedTextMessageDTO;

public interface UazapiService {

    void sendTextMessage(UazapiReceivedTextMessageDTO receivedMessageDTO);
    void sendMediaMessage(UazapiReceivedMediaMessageDTO receivedMessageDTO);
}
