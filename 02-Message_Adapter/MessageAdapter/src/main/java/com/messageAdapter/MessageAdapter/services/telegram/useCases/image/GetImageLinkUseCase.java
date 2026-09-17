package com.messageAdapter.MessageAdapter.services.telegram.useCases.image;

import com.messageAdapter.MessageAdapter.dto.telegram.common.TelegramFileResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GetImageLinkUseCase {

    private final RestClient getImageLinkClient;

    public GetImageLinkUseCase(@Qualifier("getFileLink") RestClient getImageLinkClient) {
        this.getImageLinkClient = getImageLinkClient;
    }


    public String getImageLinkUseCase(String fileID, String botToken) {

        String fileUri = botToken + "/getFile?file_id=" + fileID;

        TelegramFileResponse result = getImageLinkClient.get()
                .uri(fileUri)
                .retrieve()
                .body(TelegramFileResponse.class);

        assert result != null;
        return result.result().filePath();



    }
}
