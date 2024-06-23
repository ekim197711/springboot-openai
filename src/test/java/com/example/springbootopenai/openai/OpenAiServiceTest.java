package com.example.springbootopenai.openai;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OpenAiServiceTest {
    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private OpenAiServiceChatGpt3 openAiServiceChatGpt3;

    @Test
    public void getModels() {
        String models = openAiService.getModels();
        System.out.println(models);
        Assertions.assertThat(models).hasSizeGreaterThan(0);
    }

    @Test
    public void tryGpt3() {
        String response = openAiServiceChatGpt3.tryGpt3(
                List.of(
                        """
                                Write a poet about the moon where the hero is named Mike and he rides a bike
                                """, "Add to the story that Mike trips in a banana peel"));

        System.out.println(response);
        Assertions.assertThat(response).hasSizeGreaterThan(0);
    }


}