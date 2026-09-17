package com.agents.messageSender.services.uazapi.usecases;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SendUazapiTextMessageUsecase {

    private final RestClient uazapiClient;

    public SendUazapiTextMessageUsecase(@Qualifier("uazapiTextMessageSenderClient") RestClient uazapiClient) {
        this.uazapiClient = uazapiClient;
    }

    public void sendUazapiTextMessage() {
        // TODO: implementar metodo
    }




}
