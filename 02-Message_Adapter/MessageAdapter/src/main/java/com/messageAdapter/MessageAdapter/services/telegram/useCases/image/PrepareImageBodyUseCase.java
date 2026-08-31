package com.messageAdapter.MessageAdapter.services.telegram.useCases.image;

import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.image.ReceivedTelegramImageMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class PrepareImageBodyUseCase {

    public SentTelegramMessageDTO prepareImageBodyUseCase(ReceivedTelegramImageMessageDTO imageMessageDTO, String transcribedImage, String messageApp) {
        return new SentTelegramMessageDTO(
                imageMessageDTO.agentID(),
                imageMessageDTO.chatID(),
                imageMessageDTO.contactName(),
                transcribedImage,
                imageMessageDTO.debounceSeconds(),
                messageApp
        );
    }
}
