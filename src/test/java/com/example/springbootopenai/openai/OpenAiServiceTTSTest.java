package com.example.springbootopenai.openai;

import com.example.springbootopenai.openai.audio.OpenAiServiceTTS;
import com.example.springbootopenai.openai.audio.VoiceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiServiceTTSTest {
    @Autowired
    private OpenAiServiceTTS openAiServiceTTS;

    @Test
    public void tts() {

        var mike = "Mike is great! Remember to like, subscribe and hit that notification bell! Thank you for watching!";
        for (VoiceType value : VoiceType.values()) {
            openAiServiceTTS.tts(value,
                    mike,
                    "mike-");
        }

    }


}