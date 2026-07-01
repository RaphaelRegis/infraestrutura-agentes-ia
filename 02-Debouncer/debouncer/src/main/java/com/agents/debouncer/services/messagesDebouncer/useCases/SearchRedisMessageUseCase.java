package com.agents.debouncer.services.messagesDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
public class SearchRedisMessageUseCase {
    private final ReactiveRedisTemplate<String, String> redisMessage;

    public Mono<String> searchRedisMessageUseCase(String keyId) {
        System.out.println("BUSCANDO MENSAGENS ANTIGAS NO REDIS...");

        return redisMessage.opsForValue()
                .get(keyId)
                .defaultIfEmpty("");
    }

}
