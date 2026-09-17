package com.messageAdapter.MessageAdapter.dto.telegram.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TelegramFileResponse(
        Result result
) {
    public record Result(
            @JsonProperty("file_path") String filePath
    ) {}
}
