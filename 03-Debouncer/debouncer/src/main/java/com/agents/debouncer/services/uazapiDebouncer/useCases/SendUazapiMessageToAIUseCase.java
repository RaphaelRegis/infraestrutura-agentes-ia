package com.agents.debouncer.services.uazapiDebouncer.useCases;

import com.agents.debouncer.dto.uazapi.SendingUazapiMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SendUazapiMessageToAIUseCase {

    public Mono<Void> sendMessageToAIUseCase(SendingUazapiMessageDTO finalMessageDTO) {

        System.out.println("ENVIANDO MENSAGEM: " + finalMessageDTO.message());

        return Mono.empty();
    }
}
