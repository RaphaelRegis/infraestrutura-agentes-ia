package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.MessageDTO;
import com.agents.debouncer.services.messagesDebouncer.useCases.UpperCaseUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class DebounceServiceImpl implements DebouncerService{

    @Override
    public Mono<Void> debounceMessages(MessageDTO messageDTO) {
        return Mono.delay(Duration.ofSeconds(messageDTO.debounceSeconds()))
                .doOnNext(tick -> System.out.println("\n\n" + UpperCaseUseCase.messageUpperCase(messageDTO.message()) + "\n\n"))
                .then();
    }
}
