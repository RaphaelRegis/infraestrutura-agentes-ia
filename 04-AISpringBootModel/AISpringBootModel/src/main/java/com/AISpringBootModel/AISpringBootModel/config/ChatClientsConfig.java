package com.AISpringBootModel.AISpringBootModel.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientsConfig {

    @Bean
    @Qualifier("telegramAI")
    public ChatClient telegramChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Default System Message")
                .build();
    }

    @Bean
    @Qualifier("whatsappAI")
    public ChatClient whatsappChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Default System Message")
                .build();
    }





}
