package com.agents.debouncer.dto;

public record SendingMessageDTO(
        String agentID,
        String contactNumber,
        String contactName,
        String message
) {
}
