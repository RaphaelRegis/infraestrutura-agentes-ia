package com.agents.debouncer.services.messagesDebouncer.useCases;

import com.agents.debouncer.dto.SendingMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SendMessageToAIUseCase {

    public Mono<Void> sendMessageToAIUseCase(SendingMessageDTO finalMessageDTO) {

        System.out.println("ENVIANDO MENSAGEM: " + finalMessageDTO.message());

        return Mono.empty();




    }
}
