package com.messageAdapter.MessageAdapter.services.telegram;

import com.messageAdapter.MessageAdapter.services.telegram.useCases.audio.*;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.image.*;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.text.PrepareTextBodyUseCase;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.common.SendToDebouncerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TelegramAdapterImpl implements TelegramAdapter{

    private PrepareTextBodyUseCase prepareTextBodyUseCase;
    private ConvertAudioToBase64UseCase convertAudioToBase64UseCase;
    private DownloadAudioUseCase downloadAudioUseCase;
    private GetAudioLinkUseCase getAudioLinkUseCase;
    private PrepareAudioBodyUseCase prepareAudioBodyUseCase;
    private TranscribeAudioUseCase transcribeAudioUseCase;
    private ConvertImageToBase64UseCase convertImageToBase64UseCase;
    private DownloadImageUseCase downloadImageUseCase;
    private GetImageLinkUseCase getImageLinkUseCase;
    private PrepareImageBodyUseCase prepareImageBodyUseCase;
    private TranscribeImageUseCase transcribeImageUseCase;
    private SendToDebouncerUseCase sendToDebouncerUseCase;

    @Override
    public String adaptTextMessage() {

        prepareTextBodyUseCase.prepareTextBodyUseCase();
        sendToDebouncerUseCase.sendToDebouncerUseCase();

        return "";
    }

    @Override
    public String adaptAudioMessage() {

        // pega o link do audio
        getAudioLinkUseCase.getAudioLinkUseCase();
        // baixa
        downloadAudioUseCase.downloadAudioUseCase();
        // converte em base64
        convertAudioToBase64UseCase.convertAudioToBase64UseCase();
        // joga para a IA
        transcribeAudioUseCase.transcribeAudioUseCase();
        // prepara o objeto com o texto do audio
        prepareAudioBodyUseCase.prepareAudioBodyUseCase();
        // manda para o debouncer
        sendToDebouncerUseCase.sendToDebouncerUseCase();

        return "";
    }

    @Override
    public String adaptImageMessage() {

        // pega o link da imagem
        getImageLinkUseCase.getImageLinkUseCase();
        // baixa
        downloadImageUseCase.downloadImageUseCase();
        // converte em base64
        convertImageToBase64UseCase.convertImageToBase64UseCase();
        // joga para a IA
        transcribeImageUseCase.transcribeImageUseCase();
        // prepara o objeto com o texto da imagem
        prepareImageBodyUseCase.prepareImageBodyUseCase();
        // manda para o debouncer
        sendToDebouncerUseCase.sendToDebouncerUseCase();

        return "";
    }
}
