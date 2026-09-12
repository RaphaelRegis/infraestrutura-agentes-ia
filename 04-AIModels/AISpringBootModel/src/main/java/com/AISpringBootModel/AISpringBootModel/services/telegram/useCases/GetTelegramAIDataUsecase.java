package com.AISpringBootModel.AISpringBootModel.services.telegram.useCases;

import com.AISpringBootModel.AISpringBootModel.dto.telegram.AITelegramDataDTO;
import com.AISpringBootModel.AISpringBootModel.dto.telegram.HistoryMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import kotlin.collections.ArrayDeque;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class GetTelegramAIDataUsecase {

    public AITelegramDataDTO getTelegramAIDataUsecase() {

        // primeiro busca os dados do agente
        // em seguida pega o historico
        // por fim prepara o objeto com tudo o que queremos
        return new AITelegramDataDTO("", null);
    }

    private List<Message> prepareMessageHistory(String systemMessage, List<HistoryMessage> historyMessages) {

        List<Message> messages = new ArrayList<>();

        messages.add(new SystemMessage(systemMessage));

        for (HistoryMessage historyMessage : historyMessages) {

            if (historyMessage.messageRole().equals("user")) {
                messages.add(new UserMessage(historyMessage.message()));
            } else {
                messages.add(new AssistantMessage(historyMessage.message()));
            }
        }

        return messages;

    }



}
