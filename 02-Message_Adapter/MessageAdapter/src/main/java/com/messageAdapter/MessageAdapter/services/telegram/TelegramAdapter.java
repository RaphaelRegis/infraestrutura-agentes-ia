package com.messageAdapter.MessageAdapter.services.telegram;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;

public interface TelegramAdapter {

    void adaptTextMessage(ReceivedTelegramTextMessageDTO textMessageDTO);

    void adaptAudioMessage(ReceivedTelegramAudioMessageDTO audioMessageDTO);

    String adaptImageMessage();



}
