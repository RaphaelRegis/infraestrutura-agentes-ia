package com.agents.debouncer.controllers;

import com.agents.debouncer.dto.MessageDTO;
import com.agents.debouncer.services.messagesDebouncer.DebouncerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class MessageController {

    private final DebouncerService debouncerService;

    @GetMapping("v1/debounceMessage")
    public ResponseEntity<String> debounceMessages(@RequestBody MessageDTO messageDTO) {
        debouncerService.debounceMessages(messageDTO).subscribe();

        return ResponseEntity.accepted().body("Texto recebido!");
    }



}
