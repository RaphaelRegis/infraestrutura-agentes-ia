package com.messageAdapter.MessageAdapter.services.telegram.useCases.image;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DownloadImageUseCase {

    private final RestClient downloadFileClient;

    public DownloadImageUseCase(@Qualifier("downloadFile") RestClient downloadFileClient) {
        this.downloadFileClient = downloadFileClient;
    }

    public byte[] downloadImageUseCase(String botToken, String filePath) {

        String fileUri = botToken + "/" + filePath;

        return downloadFileClient.get()
                .uri(fileUri)
                .retrieve()
                .body(byte[].class);
    }



}
