package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.MessageDTO;
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
    public Mono<Void> debounceMessages(MessageDTO messageDTO) {

        String keyId = getKeyIdUseCase.getKeyIdUseCase(messageDTO.agentID(), messageDTO.contactNumber(), messageDTO.contactName());

        return searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)
                .map(oldMessageString -> {
                    return consolidateMessagesUseCase.consolidateMessagesUseCase(oldMessageString, messageDTO.message());
                })
                .flatMap(messageToSave -> saveRedisMessageUseCase.saveRedisMessageUseCase(keyId, messageToSave))
                .then(Mono.delay(Duration.ofSeconds(messageDTO.debounceSeconds())))
                .then(Mono.defer(() -> searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)))
                .flatMap(fullMessage -> {
                    if (true/*fullMessage.equals(messageDTO.message()) || fullMessage*/) { // TODO: RESOLVER LOGICA PARA CAIR NESSE CASO
                        return deleteRedisMessageUseCase.deleteRedisMessageUseCase(keyId)
                                        .then(sendMessageToAIUseCase.sendMessageToAIUseCase(messageDTO));
                    } else {
                        return Mono.empty();
                    }
                })
                .then();
    }
}
