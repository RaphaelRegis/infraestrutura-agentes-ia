package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.MessageDTO;
import reactor.core.publisher.Mono;

public interface DebouncerService {

    Mono<Void> debounceMessages(MessageDTO message);
}
