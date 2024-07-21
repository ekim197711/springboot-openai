package com.example.springbootopenai.openai.transcription;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TranscriptionServiceTest {

    @Autowired
    TranscriptionService transcriptionService;

    @Test
    void doTranscriptionOfFile() {
        String result = transcriptionService.doTranscriptionOfFile("mike-_alloy_4074582332552155882.mp3");
        System.out.println(result);
        Assertions.assertThat(result).isNotBlank();
        Assertions.assertThat(result.length()).isGreaterThan(10);
    }

    @Test
    void doTranscriptionOfFileCake() {
        String result = transcriptionService.doTranscriptionOfFile("Kage.mp4");
        System.out.println(result);
        Assertions.assertThat(result).isNotBlank();
        Assertions.assertThat(result.length()).isGreaterThan(10);
    }
}