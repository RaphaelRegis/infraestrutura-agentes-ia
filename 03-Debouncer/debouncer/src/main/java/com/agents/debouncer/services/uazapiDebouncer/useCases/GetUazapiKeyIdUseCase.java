package com.agents.debouncer.services.uazapiDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetUazapiKeyIdUseCase {

    public String getKeyIdUseCase(String chatID) {
        System.out.println("OBTENDO KEY DAS MENSAGENS...");
        return chatID;
    }
}
