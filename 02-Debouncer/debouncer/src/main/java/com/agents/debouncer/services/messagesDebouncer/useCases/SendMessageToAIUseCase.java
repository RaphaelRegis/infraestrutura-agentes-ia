package com.agents.debouncer.services.messagesDebouncer.useCases;

import com.agents.debouncer.dto.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SendMessageToAIUseCase {

    public Mono<Void> sendMessageToAIUseCase(MessageDTO finalMessageDTO) {

        System.out.println("ENVIANDO MENSAGEM: " + finalMessageDTO.message());

        return Mono.empty();




    }
}
