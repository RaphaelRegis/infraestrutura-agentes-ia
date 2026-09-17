package com.agents.messageSender.services.uazapi.usecases;

import com.agents.messageSender.dto.uazapi.UazapiReceivedTextMessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SendUazapiTextMessageUsecase {

    private final RestClient uazapiClient;

    public SendUazapiTextMessageUsecase(@Qualifier("uazapiTextMessageSenderClient") RestClient uazapiClient) {
        this.uazapiClient = uazapiClient;
    }

    public void sendUazapiTextMessage(UazapiReceivedTextMessageDTO dto) {
        uazapiClient.post()
                .header("token", dto.token())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }
}

