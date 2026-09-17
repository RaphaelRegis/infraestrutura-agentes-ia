package com.agents.messageSender.services.uazapi.usecases;

import com.agents.messageSender.dto.uazapi.UazapiReceivedMediaMessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SendUazapiMediaMessageUsecase {

    private final RestClient uazapiClient;

    public SendUazapiMediaMessageUsecase(@Qualifier("uazapiMediaMessageSenderClient") RestClient uazapiClient) {
        this.uazapiClient = uazapiClient;
    }

    public void sendUazapiMediaMessage(UazapiReceivedMediaMessageDTO dto) {
        uazapiClient.post()
                .header("token", dto.token())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }
}

