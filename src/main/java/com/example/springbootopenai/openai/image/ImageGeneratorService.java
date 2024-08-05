package com.example.springbootopenai.openai.image;

import com.example.springbootopenai.openai.HeaderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageGeneratorService {
    private final static String URL_IMAGE_GENERATION = "https://api.openai.com/v1/images/generations";
    private final HeaderService headerService;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * curl https://api.openai.com/v1/images/generations \
     * -H "Content-Type: application/json" \
     * -H "Authorization: Bearer $OPENAI_API_KEY" \
     * -d '{
     * "model": "dall-e-3",
     * "prompt": "a white siamese cat",
     * "n": 1,
     * "size": "1024x1024"
     * }'
     *
     * @param prompt
     * @return
     */
    public String generateImage(String prompt) throws JsonProcessingException {
        HttpHeaders httpHeaders = headerService.headersImageGeneration();
        Map<Object, Object> requestDataMap = new HashMap<>();
        requestDataMap.put("model", "dall-e-3");
        requestDataMap.put("prompt", prompt);
        requestDataMap.put("n", 1);
        requestDataMap.put("size", "1024x1024");
        String data = new ObjectMapper().writeValueAsString(requestDataMap);
        log.info("Send data " + data);
        RequestEntity<Map<Object, Object>> request = RequestEntity
                .post(URI.create(URL_IMAGE_GENERATION))
                .headers(httpHeaders)
                .body(requestDataMap);

        String responseString = restTemplate.exchange(request, String.class).getBody();
        log.info("Response from image api is {}", responseString);
        return responseString;
    }

}
