package com.AISpringBootModel.AISpringBootModel.services.telegram.useCases;

import com.AISpringBootModel.AISpringBootModel.dto.telegram.AITelegramDataDTO;
import org.apache.catalina.User;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerWithTelegramAIUsecase {

    private final ChatClient chatClient;

    public AnswerWithTelegramAIUsecase(@Qualifier("telegramAI") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String answerWitAIUsecase(AITelegramDataDTO aiTelegramDataDTO, String newMessage) {

        List<Message> messages = aiTelegramDataDTO.historyMessages();
        messages.add(new UserMessage(newMessage));
        Prompt prompt = new Prompt(messages);

        return chatClient
                .prompt(prompt)
                .call()
                .content();

    }



}
