package com.messageAdapter.MessageAdapter.services.telegram.useCases.common;

import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class HandleFinalMessageUseCase {

    public void handleFinalMessageUseCase(SentTelegramMessageDTO debouncerBody, Boolean paused) {

        if (paused) {
            // salva mensagem no contexto

        } else {
            // envia para debouncer
        }

    }



}
