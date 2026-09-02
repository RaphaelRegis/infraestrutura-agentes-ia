package com.AISpringBootModel.AISpringBootModel.services.telegram;

import com.AISpringBootModel.AISpringBootModel.services.common.useCases.AnswerWithAIUsecase;
import com.AISpringBootModel.AISpringBootModel.services.common.useCases.SaveResultsUsecase;
import com.AISpringBootModel.AISpringBootModel.services.common.useCases.SendToQueueUsecase;
import com.AISpringBootModel.AISpringBootModel.services.telegram.useCases.GetTelegramAIDataUsecase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AITelegramImpl implements AITelegram {

    private GetTelegramAIDataUsecase getTelegramAIDataUsecase;
    private AnswerWithAIUsecase answerWithAIUsecase;
    private SaveResultsUsecase saveResultsUsecase;
    private SendToQueueUsecase sendToQueueUsecase;

    @Override
    public void answer() {

        // pega todos os dados do agente relacionados a IA (prompt, janela de contexto e RAG)
        String AIData = getTelegramAIDataUsecase.getTelegramAIDataUsecase();

        // manda a IA processar a request
        String AIAnswer = answerWithAIUsecase.answerWitAIUsecase();

        // salva todos os resultados no database
        saveResultsUsecase.saveResultsUsecase();

        // joga a resposta no rabbitmq
        sendToQueueUsecase.sendToQueueUsecase();

    }
}
