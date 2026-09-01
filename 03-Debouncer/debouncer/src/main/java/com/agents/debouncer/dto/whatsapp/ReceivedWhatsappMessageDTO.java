package com.agents.debouncer.dto.whatsapp;

public record ReceivedWhatsappMessageDTO(
        String agentID,
        String contactNumber,
        String contactName,
        String conversationID,
        String message,
        Integer debounceSeconds
) {}
