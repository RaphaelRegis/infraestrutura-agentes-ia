package com.messageAdapter.MessageAdapter.services.telegram.useCases.text;

import com.messageAdapter.MessageAdapter.dto.telegram.ReceivedTelegramTextMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.SentTelegramMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class PrepareTextBodyUseCase {

    public SentTelegramMessageDTO prepareTextBodyUseCase(ReceivedTelegramTextMessageDTO textMessageDTO) {

        return new SentTelegramMessageDTO(
                textMessageDTO.agentID(),
                textMessageDTO.chatID(),
                textMessageDTO.contactName(),
                textMessageDTO.message(),
                textMessageDTO.debounceSeconds(),
                "Telegram"
        );
    }




}
