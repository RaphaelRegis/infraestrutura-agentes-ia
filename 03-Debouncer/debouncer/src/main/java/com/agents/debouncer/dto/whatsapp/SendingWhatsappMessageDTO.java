package com.agents.debouncer.dto.whatsapp;

public record SendingWhatsappMessageDTO(
        String agentID,
        String contactNumber,
        String contactName,
        String conversationID,
        String message
) {
}
