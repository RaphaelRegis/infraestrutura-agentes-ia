package com.agents.messageSender.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class MessageSenderClientsConfig {

    @Bean
    @Qualifier("uazapiTextMessageSenderClient")
    public RestClient sendUazapiTextMessage() {
        return RestClient.builder()
                .baseUrl("https://uazapi/send/text")
                .build();
    }

    @Bean
    @Qualifier("uazapiMediaMessageSenderClient")
    public RestClient sendUazapiMediaMessage() {
        return RestClient.builder()
                .baseUrl("https://uazapi/send/media")
                .build();
    }

}
