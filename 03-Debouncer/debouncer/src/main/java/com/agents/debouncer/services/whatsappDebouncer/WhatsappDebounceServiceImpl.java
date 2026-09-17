package com.agents.debouncer.services.whatsappDebouncer;

import com.agents.debouncer.dto.whatsapp.ReceivedWhatsappMessageDTO;
import com.agents.debouncer.dto.whatsapp.SendingWhatsappMessageDTO;
import com.agents.debouncer.services.whatsappDebouncer.useCases.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@AllArgsConstructor
public class WhatsappDebounceServiceImpl implements WhatsappDebouncerService {

    private final GetWhatsappKeyIdUseCase getKeyIdUseCase;
    private final SearchRedisWhatsappMessageUseCase searchRedisMessageUseCase;
    private final ConsolidateWhatsappMessagesUseCase consolidateMessagesUseCase;
    private final SaveRedisWhatsappMessageUseCase saveRedisMessageUseCase;
    private final DeleteRedisWhatsappMessageUseCase deleteRedisMessageUseCase;
    private final SendWhatsappMessageToAIUseCase sendMessageToAIUseCase;

    @Override
    public Mono<Void> debounceMessages(ReceivedWhatsappMessageDTO messageDTO) {

        String keyId = getKeyIdUseCase.getKeyIdUseCase(messageDTO.conversationID());

        return searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)
                .map(oldMessageString -> consolidateMessagesUseCase.consolidateMessagesUseCase(oldMessageString, messageDTO.message()))
                .flatMap(consolidatedMessage ->
                        saveRedisMessageUseCase.saveRedisMessageUseCase(keyId, consolidatedMessage)
                                .then(Mono.delay(Duration.ofSeconds(messageDTO.debounceSeconds())))
                                .then(Mono.defer(() -> searchRedisMessageUseCase.searchRedisMessageUseCase(keyId)))
                                .flatMap(fullMessage -> {
                                    if (fullMessage.equals(consolidatedMessage)) {
                                        SendingWhatsappMessageDTO sendingMessageDTO = new SendingWhatsappMessageDTO(
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
