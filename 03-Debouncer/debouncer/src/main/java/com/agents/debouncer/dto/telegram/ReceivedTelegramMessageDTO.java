package com.agents.debouncer.dto.telegram;

public record ReceivedTelegramMessageDTO(
        String agentID,
        String contactName,
        String chatID,
        String message,
        Integer debounceSeconds
) {
}
