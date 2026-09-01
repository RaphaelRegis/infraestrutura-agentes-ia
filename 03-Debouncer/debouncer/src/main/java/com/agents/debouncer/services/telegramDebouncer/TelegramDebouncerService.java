package com.agents.debouncer.services.telegramDebouncer;

import com.agents.debouncer.dto.telegram.ReceivedTelegramMessageDTO;
import com.agents.debouncer.dto.whatsapp.ReceivedWhatsappMessageDTO;
import reactor.core.publisher.Mono;

public interface TelegramDebouncerService {

    Mono<Void> debounceMessages(ReceivedTelegramMessageDTO message);
}
