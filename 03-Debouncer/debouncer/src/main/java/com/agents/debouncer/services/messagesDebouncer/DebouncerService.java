package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.ReceivedMessageDTO;
import reactor.core.publisher.Mono;

public interface DebouncerService {

    Mono<Void> debounceMessages(ReceivedMessageDTO message);
}
