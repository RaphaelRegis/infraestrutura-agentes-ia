package com.messageAdapter.MessageAdapter.services.telegram.useCases.common;

import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import com.messageAdapter.MessageAdapter.entities.telegram.ConversationMessage;
import com.messageAdapter.MessageAdapter.repositories.telegram.ConversationMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class HandleFinalMessageUseCase {

    private final ConversationMessageRepository conversationMessageRepository;

    public void handleFinalMessageUseCase(SentTelegramMessageDTO debouncerBody, Boolean paused) {

        if (paused) {
            // salva mensagem no contexto
            ConversationMessage conversationMessage = ConversationMessage.builder()
                    .chatID(debouncerBody.chatID())
                    .message(debouncerBody.message())
                    .fromUser(true)
                    .messageTimestamp(LocalDateTime.now())
                    .build();

            conversationMessageRepository.save(conversationMessage);

        } else {
            // envia para debouncer
        }

    }



}
