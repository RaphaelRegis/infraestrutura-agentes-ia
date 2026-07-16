package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.ReceivedMessageDTO;
import com.agents.debouncer.dto.SendingMessageDTO;
import com.agents.debouncer.services.messagesDebouncer.useCases.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class DebounceServiceImpl implements DebouncerService{

    private final GetKeyIdUseCase getKeyIdUseCase;
    private final SearchRedisMessageUseCase searchRedisMessageUseCase;
    private final ConsolidateMessagesUseCase consolidateMessagesUseCase;
    private final SaveRedisMessageUseCase saveRedisMessageUseCase;
    private final DeleteRedisMessageUseCase deleteRedisMessageUseCase;
    private final SendMessageToAIUseCase sendMessageToAIUseCase;

    @Override
    public Mono<Void> debounceMessages(ReceivedMessageDTO messageDTO) {

        String keyId = getKeyIdUseCase.getKeyIdUseCase(messageDTO.conversationID());

        return searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)
                .map(oldMessageString -> consolidateMessagesUseCase.consolidateMessagesUseCase(oldMessageString, messageDTO.message()))
                .flatMap(consolidatedMessage ->
                        saveRedisMessageUseCase.saveRedisMessageUseCase(keyId, consolidatedMessage)
                                .then(Mono.delay(Duration.ofSeconds(messageDTO.debounceSeconds())))
                                .then(Mono.defer(() -> searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)))
                                .flatMap(fullMessage -> {
                                    if (fullMessage.equals(consolidatedMessage)) {
                                        SendingMessageDTO sendingMessageDTO = new SendingMessageDTO(
                                                messageDTO.agentID(),
                                                messageDTO.contactNumber(),
                                                messageDTO.contactName(),
                                                messageDTO.conversationID(),
                                                consolidatedMessage
                                        );

                                        return sendMessageToAIUseCase.sendMessageToAIUseCase(sendingMessageDTO)
                                                .then(deleteRedisMessageUseCase.deleteRedisMessageUseCase(keyId));
                                    } else {
                                        System.out.println("PULANDO MENSAGEM...");
                                        return Mono.empty();
                                    }
                                })
                )
                .then();

    }
}
