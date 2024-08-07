package com.example.springbootopenai.openai.image;

import com.example.springbootopenai.openai.HeaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageEditService {
    private final static String URL_IMAGE_GENERATION = "https://api.openai.com/v1/images/edits";
    private final HeaderService headerService;
    private final RestTemplate restTemplate = new RestTemplate();
//
//    curl https://api.openai.com/v1/images/edits \
//            -H "Authorization: Bearer $OPENAI_API_KEY" \
//            -F model="dall-e-2" \
//            -F image="@sunlit_lounge.png" \
//            -F mask="@mask.png" \
//            -F prompt="A sunlit indoor lounge area with a pool containing a flamingo" \
//            -F n=1 \
//            -F size="1024x1024"
    public String editImage(String prompt) throws JsonProcessingException {
        MultiValueMap<String, Object> dataToPost =  new LinkedMultiValueMap<>();
        dataToPost.add("model", "dall-e-2");
        dataToPost.add("image", new FileSystemResource(new File("images/mike1_1024.png")));
        dataToPost.add("mask", new FileSystemResource(new File("images/mike1_1024_nosky.png")));
        dataToPost.add("prompt", "Vacation image with balloon festival in the background");
        dataToPost.add("n", 1);
//        dataToPost.add("size", "4032x3024");
        dataToPost.add("size", "1024x1024");
        ;
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(dataToPost, headerService.headersMultipartFormData());
        log.info("Send image to edit");
        ResponseEntity<String> response = restTemplate.postForEntity(URL_IMAGE_GENERATION, requestEntity, String.class);
        log.info("Response: {}",response.getBody());
        return response.getBody();
    }

}
