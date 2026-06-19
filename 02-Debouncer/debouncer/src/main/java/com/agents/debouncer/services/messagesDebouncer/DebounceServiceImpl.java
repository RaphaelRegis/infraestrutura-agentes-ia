package com.agents.debouncer.services.messagesDebouncer;

import com.agents.debouncer.dto.MessageDTO;
import com.agents.debouncer.services.messagesDebouncer.useCases.UpperCaseUseCase;
import org.springframework.stereotype.Service;

@Service
public class DebounceServiceImpl implements DebouncerService{

    @Override
    public String debounceMessages(MessageDTO messageDTO) {

        return UpperCaseUseCase.messageUpperCase(messageDTO.message());
    }
}
