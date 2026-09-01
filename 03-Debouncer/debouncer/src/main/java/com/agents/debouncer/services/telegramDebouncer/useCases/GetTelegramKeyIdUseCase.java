package com.agents.debouncer.services.telegramDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetTelegramKeyIdUseCase {

    public String getKeyIdUseCase(String conversationID) {
        System.out.println("OBTENDO KEY DAS MENSAGENS...");
        return conversationID;
    }
}
