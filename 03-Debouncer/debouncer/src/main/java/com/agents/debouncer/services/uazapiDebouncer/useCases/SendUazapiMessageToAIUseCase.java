package com.agents.debouncer.services.uazapiDebouncer.useCases;

import com.agents.debouncer.dto.uazapi.SendingUazapiMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class SendUazapiMessageToAIUseCase {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final String queueName = "aimodeln8n-queue";

    public Mono<Void> sendMessageToAIUseCase(SendingUazapiMessageDTO finalMessageDTO) {

        System.out.println("ENVIANDO MENSAGEM: " + finalMessageDTO.message());

        String json = objectMapper.writeValueAsString(finalMessageDTO);

        Message message = MessageBuilder
                .withBody(json.getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.send(queueName, message);

        return Mono.empty();
    }
}
