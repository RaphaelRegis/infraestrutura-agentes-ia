package com.AISpringBootModel.AISpringBootModel.config;


import com.AISpringBootModel.AISpringBootModel.services.tools.telegram.AITelegramTools;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ChatClientsConfig {

    private AITelegramTools aiTelegramTools;

    @Bean
    @Qualifier("telegramAI")
    public ChatClient telegramChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Default System Message")
                .defaultTools(aiTelegramTools)
                .build();
    }

    @Bean
    @Qualifier("whatsappAI")
    public ChatClient whatsappChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Default System Message")
                .defaultTools()
                .build();
    }

}
