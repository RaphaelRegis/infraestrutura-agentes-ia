package com.agents.debouncer.dto.uazapi;

public record ReceivedUazapiMessageDTO(
        String agentID,
        String chatID,
        String contactName,
        String token,
        String messageType,
        String messageMechanism,
        String message,
        String debounceSeconds
) {}
