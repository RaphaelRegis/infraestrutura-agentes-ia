package com.agents.debouncer.dto;

public record ReceivedMessageDTO(
        String agentID,
        String contactNumber,
        String contactName,
        String conversationID,
        String message,
        Integer debounceSeconds
) {}
