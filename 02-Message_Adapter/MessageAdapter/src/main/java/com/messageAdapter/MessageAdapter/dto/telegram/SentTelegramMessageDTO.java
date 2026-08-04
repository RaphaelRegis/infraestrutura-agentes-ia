package com.messageAdapter.MessageAdapter.dto.telegram;

public record SentTelegramMessageDTO(
        String agentID,
        String chatID,
        String contactName,
        String message,
        Integer debounceSeconds,
        String messageApp
) {
}
