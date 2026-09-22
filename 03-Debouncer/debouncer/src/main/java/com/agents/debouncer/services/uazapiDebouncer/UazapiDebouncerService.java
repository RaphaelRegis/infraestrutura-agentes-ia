package com.agents.debouncer.services.uazapiDebouncer;

import com.agents.debouncer.dto.uazapi.ReceivedUazapiMessageDTO;
import reactor.core.publisher.Mono;

public interface UazapiDebouncerService {

    Mono<Void> debounceMessages(ReceivedUazapiMessageDTO message);
}
