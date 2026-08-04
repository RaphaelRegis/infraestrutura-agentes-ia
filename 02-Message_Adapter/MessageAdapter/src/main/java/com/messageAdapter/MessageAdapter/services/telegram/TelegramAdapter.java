package com.messageAdapter.MessageAdapter.services.telegram;

import com.messageAdapter.MessageAdapter.dto.telegram.ReceivedTelegramTextMessageDTO;

public interface TelegramAdapter {

    public void adaptTextMessage(ReceivedTelegramTextMessageDTO textMessageDTO);
    public String adaptAudioMessage();
    public String adaptImageMessage();



}
