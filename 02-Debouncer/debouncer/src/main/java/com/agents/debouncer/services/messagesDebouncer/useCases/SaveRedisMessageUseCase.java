package com.agents.debouncer.services.messagesDebouncer.useCases;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SaveRedisMessageUseCase {
    private final ReactiveRedisTemplate<String, String> redisMessage;

    public Mono<Void> saveRedisMessageUseCase(String keyId, String message) {
        System.out.println("SALVANDO MENSAGEM NO REDIS: \nKEY: " + keyId + "\nMENSAGEM: " + message);
        return redisMessage.opsForValue().set(keyId, message)
                .then();
    }
}
