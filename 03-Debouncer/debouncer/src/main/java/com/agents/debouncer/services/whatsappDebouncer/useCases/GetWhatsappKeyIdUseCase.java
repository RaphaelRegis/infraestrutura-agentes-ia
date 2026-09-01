package com.agents.debouncer.services.whatsappDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetWhatsappKeyIdUseCase {

    public String getKeyIdUseCase(String conversationID) {
        System.out.println("OBTENDO KEY DAS MENSAGENS...");
        return conversationID;
    }
}
