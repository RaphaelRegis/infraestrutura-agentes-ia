package com.messageAdapter.MessageAdapter.services.telegram;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;

import java.io.IOException;

public interface TelegramAdapter {

    void adaptTextMessage(ReceivedTelegramTextMessageDTO textMessageDTO);

    void adaptAudioMessage(ReceivedTelegramAudioMessageDTO audioMessageDTO) throws IOException, InterruptedException;

    String adaptImageMessage();



}
