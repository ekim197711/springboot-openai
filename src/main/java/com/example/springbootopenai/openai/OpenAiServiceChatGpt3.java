package com.example.springbootopenai.openai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAiServiceChatGpt3 {
    RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders headersTryGpt3() {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + openaiApiKey);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return httpHeaders;
    }

    public String tryGpt3(List<String> questions) {
        String url = "https://api.openai.com/v1/chat/completions";
        Map<String, Object> dataToPost = new HashMap<>();
        dataToPost.put("model", "gpt-3.5-turbo");
        dataToPost.put("messages", questions.stream().map(s -> (Map.of(
                "role", "user",
                "content", s
        ))).collect(Collectors.toList()));
        dataToPost.put("temperature", 0.9);


        RequestEntity<Map<String, Object>> request = RequestEntity.post(
                        URI.create(url))
                .headers(headersTryGpt3())
                .body(dataToPost);
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response.getBody();
    }
}
