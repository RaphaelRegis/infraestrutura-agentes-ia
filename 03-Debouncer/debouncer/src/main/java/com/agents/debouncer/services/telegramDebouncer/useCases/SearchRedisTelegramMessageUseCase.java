package com.agents.debouncer.services.telegramDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
public class SearchRedisTelegramMessageUseCase {
    private final ReactiveRedisTemplate<String, String> redisMessage;

    public Mono<String> searchRedisMessageUseCase(String keyId) {
        System.out.println("BUSCANDO MENSAGENS NO REDIS...");

        return redisMessage.opsForValue()
                .get(keyId)
                .defaultIfEmpty("");
    }

}
