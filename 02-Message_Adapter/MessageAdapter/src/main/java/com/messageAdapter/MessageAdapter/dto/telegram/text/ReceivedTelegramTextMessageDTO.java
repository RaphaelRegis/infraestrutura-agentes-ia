package com.messageAdapter.MessageAdapter.dto.telegram.text;

public record ReceivedTelegramTextMessageDTO(
        String agentID,
        String chatID,
        String contactName,
        String message,
        Boolean isPaused,
        Integer debounceSeconds
) {
}
