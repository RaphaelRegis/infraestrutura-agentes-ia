package com.agents.messageSender.services.uazapi.usecases;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class SendUazapiTextMessageUsecase {

    private final RestClient uazapiClient;

    public SendUazapiTextMessageUsecase(@Qualifier("uazapiTextMessageSenderClient") RestClient uazapiClient) {
        this.uazapiClient = uazapiClient;
    }

    public void sendUazapiTextMessage(String token, String number, String text) {
        uazapiClient.post()
                .header("token", token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Map.of("number", number, "text", text))
                .retrieve()
                .toBodilessEntity();
    }
}
