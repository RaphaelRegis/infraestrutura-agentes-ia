package com.agents.messageSender.services.uazapi;

import com.agents.messageSender.dto.uazapi.UazapiReceivedMediaMessageDTO;
import com.agents.messageSender.dto.uazapi.UazapiReceivedTextMessageDTO;
import com.agents.messageSender.services.uazapi.usecases.SendUazapiMediaMessageUsecase;
import com.agents.messageSender.services.uazapi.usecases.SendUazapiTextMessageUsecase;
import com.agents.messageSender.services.uazapi.usecases.SplitUazapiTextMessageUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class UazapiServiceImpl implements UazapiService {

    private static final int MIN_DELAY_MS = 1000;
    private static final int MAX_DELAY_MS = 3000;

    private final SplitUazapiTextMessageUsecase splitUazapiTextMessageUsecase;
    private final SendUazapiTextMessageUsecase sendUazapiTextMessageUsecase;
    private final SendUazapiMediaMessageUsecase sendUazapiMediaMessageUsecase;
    private final Random random = new Random();

    @Override
    public void sendTextMessage(UazapiReceivedTextMessageDTO dto) {
        List<String> lines = splitUazapiTextMessageUsecase.execute(dto.text());

        for (int i = 0; i < lines.size(); i++) {
            //sendUazapiTextMessageUsecase.sendUazapiTextMessage(dto.token(), dto.number(), lines.get(i));
            log.info("Mensagem {}/{} enviada para {}: \"{}\"", i + 1, lines.size(), dto.number(), lines.get(i));

            if (i < lines.size() - 1) {
                int delayMs = MIN_DELAY_MS + random.nextInt(MAX_DELAY_MS - MIN_DELAY_MS + 1);
                log.debug("Aguardando {}ms antes da próxima mensagem...", delayMs);
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.warn("Envio interrompido após {}/{} mensagens", i + 1, lines.size());
                    return;
                }
            }
        }
    }

    @Override
    public void sendMediaMessage(UazapiReceivedMediaMessageDTO receivedMessageDTO) {
        sendUazapiMediaMessageUsecase.sendUazapiMediaMessage(receivedMessageDTO);
    }
}
