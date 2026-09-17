package com.agents.debouncer.services.telegramDebouncer;

import com.agents.debouncer.dto.telegram.ReceivedTelegramMessageDTO;
import com.agents.debouncer.dto.telegram.SendingTelegramMessageDTO;
import com.agents.debouncer.services.telegramDebouncer.useCases.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class TelegramDebounceServiceImpl implements TelegramDebouncerService {

    private final GetTelegramKeyIdUseCase getKeyIdUseCase;
    private final SearchRedisTelegramMessageUseCase searchRedisMessageUseCase;
    private final ConsolidateTelegramMessagesUseCase consolidateMessagesUseCase;
    private final SaveRedisTelegramMessageUseCase saveRedisMessageUseCase;
    private final DeleteRedisTelegramMessageUseCase deleteRedisMessageUseCase;
    private final SendTelegramMessageToAIUseCase sendMessageToAIUseCase;

    @Override
    public Mono<Void> debounceMessages(ReceivedTelegramMessageDTO messageDTO) {

        String keyId = getKeyIdUseCase.getKeyIdUseCase(messageDTO.conversationID());

        return searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)
                .map(oldMessageString -> consolidateMessagesUseCase.consolidateMessagesUseCase(oldMessageString, messageDTO.message()))
                .flatMap(consolidatedMessage ->
                        saveRedisMessageUseCase.saveRedisMessageUseCase(keyId, consolidatedMessage)
                                .then(Mono.delay(Duration.ofSeconds(messageDTO.debounceSeconds())))
                                .then(Mono.defer(() -> searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)))
                                .flatMap(fullMessage -> {
                                    if (fullMessage.equals(consolidatedMessage)) {
                                        SendingTelegramMessageDTO sendingMessageDTO = new SendingTelegramMessageDTO(
                                                messageDTO.agentID(),
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
