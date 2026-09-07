package com.AISpringBootModel.AISpringBootModel.services.telegram.useCases;

import com.AISpringBootModel.AISpringBootModel.dto.telegram.AITelegramDataDTO;
import com.AISpringBootModel.AISpringBootModel.services.tools.telegram.AITelegramTools;
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
    private final AITelegramTools agentTools;

    public AnswerWithTelegramAIUsecase(@Qualifier("telegramAI") ChatClient chatClient, AITelegramTools agentTools) {
        this.chatClient = chatClient;
        this.agentTools = agentTools;
    }

    public String answerWitAIUsecase(AITelegramDataDTO aiTelegramDataDTO, String newMessage) {

        List<Message> messages = aiTelegramDataDTO.historyMessages();
        messages.add(new UserMessage(newMessage));
        Prompt prompt = new Prompt(messages);

        return chatClient
                .prompt(prompt)
                .tools(agentTools)
                .call()
                .content();
    }

}
