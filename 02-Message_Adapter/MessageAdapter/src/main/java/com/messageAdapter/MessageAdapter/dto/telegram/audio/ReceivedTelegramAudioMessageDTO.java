package com.messageAdapter.MessageAdapter.dto.telegram.audio;

public record ReceivedTelegramAudioMessageDTO(
        String agentID,
        String chatID,
        String contactName,
        String fileID,
        String botToken,
        Boolean isPaused,
        Integer debounceSeconds
) {
}
