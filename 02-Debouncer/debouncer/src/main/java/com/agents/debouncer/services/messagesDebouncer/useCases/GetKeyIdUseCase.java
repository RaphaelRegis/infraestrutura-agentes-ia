package com.agents.debouncer.services.messagesDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetKeyIdUseCase {

    public String getKeyIdUseCase(String conversationID) {
        System.out.println("OBTENDO KEY DAS MENSAGENS...");
        return conversationID;
    }
}
