package com.messageAdapter.MessageAdapter.dto.telegram.common;

public record SentTelegramMessageDTO(
        String agentID,
        String chatID,
        String contactName,
        String message,
        Integer debounceSeconds,
        String messageApp
) {
}
