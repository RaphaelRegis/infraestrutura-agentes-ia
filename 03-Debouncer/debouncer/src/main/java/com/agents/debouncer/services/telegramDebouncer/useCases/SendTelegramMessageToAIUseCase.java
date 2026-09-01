package com.agents.debouncer.services.telegramDebouncer.useCases;

import com.agents.debouncer.dto.telegram.SendingTelegramMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SendTelegramMessageToAIUseCase {

    public Mono<Void> sendMessageToAIUseCase(SendingTelegramMessageDTO finalMessageDTO) {

        System.out.println("ENVIANDO MENSAGEM: " + finalMessageDTO.message());

        return Mono.empty();




    }
}
