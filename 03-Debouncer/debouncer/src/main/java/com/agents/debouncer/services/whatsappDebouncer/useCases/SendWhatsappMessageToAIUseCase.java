package com.agents.debouncer.services.whatsappDebouncer.useCases;

import com.agents.debouncer.dto.whatsapp.SendingWhatsappMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SendWhatsappMessageToAIUseCase {

    public Mono<Void> sendMessageToAIUseCase(SendingWhatsappMessageDTO finalMessageDTO) {

        System.out.println("ENVIANDO MENSAGEM: " + finalMessageDTO.message());

        return Mono.empty();




    }
}
