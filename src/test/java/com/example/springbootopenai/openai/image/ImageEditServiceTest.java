package com.example.springbootopenai.openai.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ImageEditServiceTest {
    @Autowired
    ImageEditService imageEditService;

    @Test
    public void editImage() throws JsonProcessingException {
        String result = imageEditService.editImage("sdffdssfd");
    }
}