package com.AISpringBootModel.AISpringBootModel.services.common.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SendToQueueUsecase {

    public void sendToQueueUsecase() {
        // envia a mensagem para a fila do RabbitMQ com os devidos dados de envio
        // essa mensagem sera consumida por um programa que ira fazer o envio
    }





}
