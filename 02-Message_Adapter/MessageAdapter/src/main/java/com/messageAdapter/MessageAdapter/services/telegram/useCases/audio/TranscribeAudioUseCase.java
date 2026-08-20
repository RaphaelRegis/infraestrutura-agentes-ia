package com.messageAdapter.MessageAdapter.services.telegram.useCases.audio;

import lombok.AllArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import com.openai.models.audio.AudioResponseFormat;

@Component
@AllArgsConstructor
public class TranscribeAudioUseCase {

    private final TranscriptionModel transcriptionModel;

    public String transcribeAudioUseCase(byte[] audioBytes) {
        ByteArrayResource resource = new ByteArrayResource(audioBytes);

        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .responseFormat(AudioResponseFormat.TEXT)
                .build();

        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(resource, options);
        return transcriptionModel.call(prompt).getResult().getOutput();
    }
}
