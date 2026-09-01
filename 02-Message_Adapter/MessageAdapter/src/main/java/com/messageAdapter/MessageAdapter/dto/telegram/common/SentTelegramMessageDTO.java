package com.messageAdapter.MessageAdapter.dto.telegram.common;

public record SentTelegramMessageDTO(
        String agentID,
        String contactName,
        String conversationID,
        String message,
        Integer debounceSeconds
) {
}
