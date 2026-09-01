package com.messageAdapter.MessageAdapter.services.telegram.useCases.audio;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class PrepareAudioBodyUseCase {

    public SentTelegramMessageDTO prepareAudioBodyUseCase(ReceivedTelegramAudioMessageDTO audioMessageDTO, String transcribedMessage) {
        return new SentTelegramMessageDTO(
                audioMessageDTO.agentID(),
                audioMessageDTO.contactName(),
                audioMessageDTO.chatID(),
                transcribedMessage,
                audioMessageDTO.debounceSeconds()
        );
    }
}
