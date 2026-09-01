package com.agents.debouncer.dto.telegram;

public record ReceivedTelegramMessageDTO(
        String agentID,
        String contactName,
        String conversationID,
        String message,
        Integer debounceSeconds
) {
}
