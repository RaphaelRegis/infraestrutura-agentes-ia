package com.messageAdapter.MessageAdapter.services.telegram;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.image.ReceivedTelegramImageMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.audio.*;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.image.*;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.text.PrepareTextBodyUseCase;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.common.HandleFinalMessageUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TelegramAdapterImpl implements TelegramAdapter{

    private PrepareTextBodyUseCase prepareTextBodyUseCase;
    private ConvertAudioToMp3UseCase convertAudioToMp3UseCase;
    private DownloadAudioUseCase downloadAudioUseCase;
    private GetAudioLinkUseCase getAudioLinkUseCase;
    private PrepareAudioBodyUseCase prepareAudioBodyUseCase;
    private TranscribeAudioUseCase transcribeAudioUseCase;
    private ConvertImageToBase64UseCase convertImageToBase64UseCase;
    private DownloadImageUseCase downloadImageUseCase;
    private GetImageLinkUseCase getImageLinkUseCase;
    private PrepareImageBodyUseCase prepareImageBodyUseCase;
    private TranscribeImageUseCase transcribeImageUseCase;
    private HandleFinalMessageUseCase handleFinalMessageUseCase;

    @Override
    public void adaptTextMessage(ReceivedTelegramTextMessageDTO textMessageDTO) {

        SentTelegramMessageDTO debouncerBody = prepareTextBodyUseCase.prepareTextBodyUseCase(textMessageDTO);
        handleFinalMessageUseCase.handleFinalMessageUseCase(debouncerBody, textMessageDTO.isPaused());

    }

    @Override
    public void adaptAudioMessage(ReceivedTelegramAudioMessageDTO audioMessageDTO) throws IOException, InterruptedException {

        String filePath = getAudioLinkUseCase.getAudioLinkUseCase(audioMessageDTO.fileID(), audioMessageDTO.botToken());
        byte[] audioFile = downloadAudioUseCase.downloadAudioUseCase(audioMessageDTO.botToken(), filePath);
        byte[] mp3AudioFile = convertAudioToMp3UseCase.convertAudioToMp3UseCase(audioFile);
        String transcribedAudio = transcribeAudioUseCase.transcribeAudioUseCase(mp3AudioFile);
        SentTelegramMessageDTO debouncerBody = prepareAudioBodyUseCase.prepareAudioBodyUseCase(audioMessageDTO, transcribedAudio);
        handleFinalMessageUseCase.handleFinalMessageUseCase(debouncerBody, audioMessageDTO.isPaused());

    }

    @Override
    public void adaptImageMessage(ReceivedTelegramImageMessageDTO imageMessageDTO) {

        String filePath = getImageLinkUseCase.getImageLinkUseCase(imageMessageDTO.fileID(), imageMessageDTO.botToken());
        byte[] imageBytes = downloadImageUseCase.downloadImageUseCase(imageMessageDTO.botToken(), filePath);
        String transcribedImage = transcribeImageUseCase.transcribeImageUseCase(imageBytes, imageMessageDTO.imageCaption());
        SentTelegramMessageDTO debouncerBody =  prepareImageBodyUseCase.prepareImageBodyUseCase(imageMessageDTO, transcribedImage);
        handleFinalMessageUseCase.handleFinalMessageUseCase(debouncerBody, imageMessageDTO.isPaused());

    }
}
