package com.agents.debouncer.services.uazapiDebouncer.useCases;

import org.springframework.stereotype.Component;

@Component
public class ConsolidateUazapiMessagesUseCase {

    public String consolidateMessagesUseCase(String oldMessage, String message) {
        System.out.println("CONSOLIDANDO AS MENSAGENS | m1: " + oldMessage + " | m2:" + message);

        if (!oldMessage.isEmpty()) {
            return oldMessage + "\n\n" + message;
        } else {
            return message;
        }
    }
}
