package com.agents.messageSender.dto.uazapi;

public record UazapiReceivedTextMessageDTO(
        String token,
        String number,
        String text
) {
}
