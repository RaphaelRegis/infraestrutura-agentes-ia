package com.agents.debouncer.services.messagesDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DeleteRedisMessageUseCase {

    private final ReactiveRedisTemplate<String, String> redisMessage;

    public Mono<Long> deleteRedisMessageUseCase(String keyId) {
        System.out.println("DELETANDO MENSAGEM DO REDIS: \nKEY: " + keyId);

        return redisMessage.delete(keyId);
    }
}
