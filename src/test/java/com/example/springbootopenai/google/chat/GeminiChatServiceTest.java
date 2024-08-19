package com.example.springbootopenai.google.chat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GeminiChatServiceTest {

    @Test
    void doPrompt() {
        String response = new GeminiChatService().doPrompt("How do I make soft boiled eggs?");
        Assertions.assertThat(response).isNotBlank();
    }
}