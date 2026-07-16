package com.agents.debouncer.dto;

public record ReceivedMessageDTO(
        String messageType,
        String agentID,
        String contactNumber,
        String contactName,
        String message,
        Integer debounceSeconds
) {}
