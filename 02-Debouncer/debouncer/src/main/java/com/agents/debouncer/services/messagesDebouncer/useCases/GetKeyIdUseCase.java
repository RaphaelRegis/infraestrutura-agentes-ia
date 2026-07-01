package com.agents.debouncer.services.messagesDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class GetKeyIdUseCase {

    public String getKeyIdUseCase(String agentID, String contactNumber, String contactName) {
        System.out.println("OBTENDO KEY DAS MENSAGENS...");
        List<String> idData = Arrays.asList(agentID, contactNumber, contactName);
        return String.join("_", idData);
    }
}
