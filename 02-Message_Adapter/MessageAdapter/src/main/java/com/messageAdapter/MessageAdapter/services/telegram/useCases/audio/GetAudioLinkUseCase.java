package com.messageAdapter.MessageAdapter.services.telegram.useCases.audio;

import com.messageAdapter.MessageAdapter.dto.telegram.common.TelegramFileResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GetAudioLinkUseCase {

    private final RestClient getAudioLinkClient;

    public GetAudioLinkUseCase(@Qualifier("getFileLink") RestClient getAudioLinkClient) {
        this.getAudioLinkClient = getAudioLinkClient;
    }

    public String getAudioLinkUseCase(String fileID, String botToken) {

        String fileUri = botToken + "/getFile?file_id=" + fileID;

        TelegramFileResponse result = getAudioLinkClient.get()
                .uri(fileUri)
                .retrieve()
                .body(TelegramFileResponse.class);

        assert result != null;
        return result.result().filePath();
    }
}
