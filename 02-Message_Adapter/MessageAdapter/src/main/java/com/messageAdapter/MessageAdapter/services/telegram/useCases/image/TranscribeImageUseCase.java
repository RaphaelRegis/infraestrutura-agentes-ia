package com.messageAdapter.MessageAdapter.services.telegram.useCases.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
public class TranscribeImageUseCase {

    //private final ChatClient chatClient;

//    public TranscribeImageUseCase(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }

    public String transcribeImageUseCase(byte[] imageBytes, String imageCaption) {

//        Resource imageResource = new ByteArrayResource(imageBytes);
//
//        // TODO: O PROMPT DA IMAGEM DEVE VIR DE REPOSITÓRIO EXTERNO
//        return chatClient.prompt()
//                .user(userMessage -> userMessage
//                        .text("")
//                        .text("Legenda da imagem: " + imageCaption)
//                        .media(MimeTypeUtils.parseMimeType(""), imageResource))
//                .call()
//                .content();

        return "Imagem transcrita MOCK";
    }
}
