package com.messageAdapter.MessageAdapter.services.telegram.useCases.common;

import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import com.messageAdapter.MessageAdapter.entities.telegram.ConversationMessage;
import com.messageAdapter.MessageAdapter.repositories.telegram.ConversationMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class HandleFinalMessageUseCase {

    private final ConversationMessageRepository conversationMessageRepository;
    private final RestClient debouncerClient;

    public void handleFinalMessageUseCase(SentTelegramMessageDTO debouncerBody, Boolean paused) {

        if (paused) {
            saveMessageInContext(debouncerBody);

        } else {
            sendToDebouncer(debouncerBody);

        }

    }

    public void saveMessageInContext(SentTelegramMessageDTO debouncerBody) {
        ConversationMessage conversationMessage = ConversationMessage.builder()
                .chatID(debouncerBody.chatID())
                .message(debouncerBody.message())
                .fromUser(true)
                .messageTimestamp(LocalDateTime.now())
                .build();

        conversationMessageRepository.save(conversationMessage);
    }

    public void sendToDebouncer(SentTelegramMessageDTO debouncerBody) {
        String debouncerUri = "/debounceMessage";

        String result = debouncerClient.post()
                .uri(debouncerUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(debouncerBody)
                .retrieve()
                .toString();
    }
}
