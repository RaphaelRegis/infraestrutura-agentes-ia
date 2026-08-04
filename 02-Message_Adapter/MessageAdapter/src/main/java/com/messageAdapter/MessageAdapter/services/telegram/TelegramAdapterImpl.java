package com.messageAdapter.MessageAdapter.services.telegram;

import com.messageAdapter.MessageAdapter.dto.telegram.audio.ReceivedTelegramAudioMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.text.ReceivedTelegramTextMessageDTO;
import com.messageAdapter.MessageAdapter.dto.telegram.common.SentTelegramMessageDTO;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.audio.*;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.image.*;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.text.PrepareTextBodyUseCase;
import com.messageAdapter.MessageAdapter.services.telegram.useCases.common.HandleFinalMessageUseCase;
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
    private HandleFinalMessageUseCase handleFinalMessageUseCase;
    private final String messageApp = "TELEGRAM";

    @Override
    public void adaptTextMessage(ReceivedTelegramTextMessageDTO textMessageDTO) {

        SentTelegramMessageDTO debouncerBody = prepareTextBodyUseCase.prepareTextBodyUseCase(textMessageDTO, this.messageApp);
        handleFinalMessageUseCase.handleFinalMessageUseCase(debouncerBody, textMessageDTO.isPaused());

    }

    @Override
    public void adaptAudioMessage(ReceivedTelegramAudioMessageDTO audioMessageDTO) {

        // pega o link do audio
        String filePath = getAudioLinkUseCase.getAudioLinkUseCase(audioMessageDTO.fileID(), audioMessageDTO.botToken());

        // baixa
        byte[] audioFile = downloadAudioUseCase.downloadAudioUseCase(audioMessageDTO.botToken(), filePath);

        // TODO: CONTINUE DAQUI
        // converte em base64
        convertAudioToBase64UseCase.convertAudioToBase64UseCase();
        // joga para a IA
        transcribeAudioUseCase.transcribeAudioUseCase();
        // prepara o objeto com o texto do audio
        prepareAudioBodyUseCase.prepareAudioBodyUseCase();
        // salva no contexto caso necessario e manda para o debouncer
        handleFinalMessageUseCase.handleFinalMessageUseCase(null, null);

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
        // salva no contexto caso necessario e manda para o debouncer
        handleFinalMessageUseCase.handleFinalMessageUseCase(null, null);

        return "";
    }
}
