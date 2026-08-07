package com.messageAdapter.MessageAdapter.dto.telegram.image;

public record ReceivedTelegramImageMessageDTO(
        String agentID,
        String chatID,
        String contactName,
        String fileID,
        String botToken,
        Boolean isPaused,
        Integer debounceSeconds
) {
}
