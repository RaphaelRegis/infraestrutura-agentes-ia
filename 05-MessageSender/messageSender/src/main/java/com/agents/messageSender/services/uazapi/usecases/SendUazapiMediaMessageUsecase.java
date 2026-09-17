package com.agents.messageSender.services.uazapi.usecases;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestClient;

public class SendUazapiMediaMessageUsecase {

    private final RestClient uazapiClient;

    public SendUazapiMediaMessageUsecase(@Qualifier("uazapiMediaMessageSenderClient") RestClient uazapiClient) {
        this.uazapiClient = uazapiClient;
    }

    public void sendUazapiMediaMessageUsecase() {
        // TODO: implementar metodo
    }
}
