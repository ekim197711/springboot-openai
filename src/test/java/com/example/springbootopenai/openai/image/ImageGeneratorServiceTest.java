package com.example.springbootopenai.openai.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageGeneratorServiceTest {
    @Autowired
    ImageGeneratorService imageGeneratorService;

    @Test
    void generateImage() throws JsonProcessingException {
        imageGeneratorService.generateImage(
                """
                        Photo of ducks being chased by an old lady with a cane.                        
                        """
        );
    }
}