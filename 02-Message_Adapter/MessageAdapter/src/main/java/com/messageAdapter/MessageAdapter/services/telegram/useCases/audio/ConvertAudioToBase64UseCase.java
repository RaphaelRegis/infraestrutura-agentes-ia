package com.messageAdapter.MessageAdapter.services.telegram.useCases.audio;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ConvertAudioToBase64UseCase {

    public byte[] convertAudioToMp3UseCase(byte[] ogaBytes) throws IOException, InterruptedException {

        Path input = Files.createTempFile("audio", ".oga");
        Path output = Files.createTempFile("audio", ".mp3");
        Files.write(input, ogaBytes);

        new ProcessBuilder("ffmpeg", "-y", "-i", input.toString(), output.toString())
                .inheritIO()
                .start()
                .waitFor();

        return Files.readAllBytes(output);
    }
}
