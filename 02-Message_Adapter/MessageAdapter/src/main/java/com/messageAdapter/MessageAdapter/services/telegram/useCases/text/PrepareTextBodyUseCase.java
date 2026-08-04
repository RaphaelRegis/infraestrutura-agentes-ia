package com.messageAdapter.MessageAdapter.services.telegram.useCases.text;

import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class PrepareTextBodyUseCase {

    public SentTelegramMessageDTO prepareTextBodyUseCase(ReceivedTelegramTextMessageDTO textMessageDTO, String messageApp) {

        return new SentTelegramMessageDTO(
                textMessageDTO.agentID(),
                textMessageDTO.chatID(),
                textMessageDTO.contactName(),
                textMessageDTO.message(),
                textMessageDTO.debounceSeconds(),
                messageApp
        );
    }




}
