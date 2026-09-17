package com.agents.debouncer.services.whatsappDebouncer;

import com.agents.debouncer.dto.whatsapp.ReceivedWhatsappMessageDTO;
import reactor.core.publisher.Mono;

public interface WhatsappDebouncerService {

    Mono<Void> debounceMessages(ReceivedWhatsappMessageDTO message);
}
