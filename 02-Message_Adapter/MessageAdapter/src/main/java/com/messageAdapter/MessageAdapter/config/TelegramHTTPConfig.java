package com.messageAdapter.MessageAdapter.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TelegramHTTPConfig {

    @Bean
    @Qualifier("getFileLink")
    public RestClient getFileLinkClient() {
        return RestClient.builder()
                .baseUrl("https://api.telegram.org/bot")
                .build();
    }

    @Bean
    @Qualifier("downloadFile")
    public RestClient downloadFileClient() {
        return RestClient.builder()
                .baseUrl("https://api.telegram.org/file/bot")
                .build();
    }

    @Bean
    @Qualifier("debouncer")
    public RestClient debouncerClient() {
        return RestClient.builder()
                .baseUrl("https://localhost:8080/api/v1")
                .build();
    }




}
