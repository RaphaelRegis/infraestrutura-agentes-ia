package com.agents.debouncer.dto.telegram;

public record SendingTelegramMessageDTO(
        String agentID,
        String contactName,
        String conversationID,
        String message
) {
}
