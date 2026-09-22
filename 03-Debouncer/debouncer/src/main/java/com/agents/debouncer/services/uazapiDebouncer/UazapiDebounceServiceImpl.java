package com.agents.debouncer.services.uazapiDebouncer;

import com.agents.debouncer.dto.uazapi.ReceivedUazapiMessageDTO;
import com.agents.debouncer.dto.uazapi.SendingUazapiMessageDTO;
import com.agents.debouncer.services.uazapiDebouncer.useCases.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class UazapiDebounceServiceImpl implements UazapiDebouncerService {

    private final GetUazapiKeyIdUseCase getKeyIdUseCase;
    private final SearchRedisUazapiMessageUseCase searchRedisMessageUseCase;
    private final ConsolidateUazapiMessagesUseCase consolidateMessagesUseCase;
    private final SaveRedisUazapiMessageUseCase saveRedisMessageUseCase;
    private final DeleteRedisUazapiMessageUseCase deleteRedisMessageUseCase;
    private final SendUazapiMessageToAIUseCase sendMessageToAIUseCase;

    @Override
    public Mono<Void> debounceMessages(ReceivedUazapiMessageDTO messageDTO) {

        String keyId = getKeyIdUseCase.getKeyIdUseCase(messageDTO.chatID());
        long debounceSeconds = Long.parseLong(messageDTO.debounceSeconds());

        return searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)
                .map(oldMessageString -> consolidateMessagesUseCase.consolidateMessagesUseCase(oldMessageString, messageDTO.message()))
                .flatMap(consolidatedMessage ->
                        saveRedisMessageUseCase.saveRedisMessageUseCase(keyId, consolidatedMessage)
                                .then(Mono.delay(Duration.ofSeconds(debounceSeconds)))
                                .then(Mono.defer(() -> searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)))
                                .flatMap(fullMessage -> {
                                    if (fullMessage.equals(consolidatedMessage)) {
                                        SendingUazapiMessageDTO sendingMessageDTO = new SendingUazapiMessageDTO(
                                                messageDTO.agentID(),
                                                messageDTO.chatID(),
                                                messageDTO.contactName(),
                                                messageDTO.contactNumber(),
                                                messageDTO.token(),
                                                messageDTO.messageType(),
                                                messageDTO.messageMechanism(),
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
