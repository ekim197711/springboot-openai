package com.example.springbootopenai.openai.image;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageEditServiceTest {
    @Autowired
    ImageEditService imageEditService;

    @Test
    public void editImage() {
        String result = imageEditService.editImage("Vacation resort with pirate ship");
        Assertions.assertThat(result).isNotBlank();
    }
}