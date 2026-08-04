package com.messageAdapter.MessageAdapter.services.telegram.useCases.audio;

import com.messageAdapter.MessageAdapter.dto.telegram.common.TelegramFileResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DownloadAudioUseCase {

    private final RestClient downloadFileClient;

    public DownloadAudioUseCase(@Qualifier("downloadFile") RestClient getAudioLinkClient) {
        this.downloadFileClient = getAudioLinkClient;
    }

    public byte[] downloadAudioUseCase(String botToken, String filePath) {

        String fileUri = botToken + "/" + filePath;

        return downloadFileClient.get()
                .uri(fileUri)
                .retrieve()
                .body(byte[].class);
    }

}
