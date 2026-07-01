package com.agents.debouncer.services.messagesDebouncer.useCases;

import com.agents.debouncer.dto.MessageDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConsolidateMessagesUseCase {

    public String consolidateMessagesUseCase(String oldMessage, String message) {
        System.out.println("CONSOLIDANDO AS MENSAGENS: \n\nm1: " + oldMessage + "\n\nm2:" + message);

        if (!oldMessage.isEmpty()) {
            return oldMessage + "\n\n" + message;
        } else {
            return message;
        }

    }

}
