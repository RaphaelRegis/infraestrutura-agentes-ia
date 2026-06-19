package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.MessageDTO;

public interface DebouncerService {

    String debounceMessages(MessageDTO message);
}
