package com.agents.messageSender.dto.uazapi;

public record UazapiReceivedMediaMessageDTO(
        String token,
        String number,
        String type,
        String file
) {
}
