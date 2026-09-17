package com.AISpringBootModel.AISpringBootModel.dto.telegram;

import org.springframework.ai.chat.messages.Message;

import java.util.List;

public record AITelegramDataDTO(
        String systemPrompt,
        List<Message> historyMessages
) {
}
