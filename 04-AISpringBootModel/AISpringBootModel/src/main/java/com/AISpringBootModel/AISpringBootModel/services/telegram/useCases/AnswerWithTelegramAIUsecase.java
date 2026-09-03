package com.AISpringBootModel.AISpringBootModel.services.telegram.useCases;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AnswerWithTelegramAIUsecase {

    private final ChatClient chatClient;

    public AnswerWithTelegramAIUsecase(@Qualifier("telegramAI") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String answerWitAIUsecase(String system, String pergunta) {

        return chatClient.prompt()
                .system(system)
                .user(pergunta)
                .call()
                .content();

    }



}
