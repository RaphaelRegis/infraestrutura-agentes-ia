package com.agents.debouncer.dto;

public record MessageDTO(
        String message,
        Integer debounceSeconds
) {
}
