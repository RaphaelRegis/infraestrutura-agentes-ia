package com.agents.debouncer.services.messagesDebouncer.useCases;

import org.springframework.stereotype.Component;

public class UpperCaseUseCase {

    public static String messageUpperCase(String message) {
        return message.toUpperCase();
    }
}
