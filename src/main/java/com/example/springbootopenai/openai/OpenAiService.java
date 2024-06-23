package com.example.springbootopenai.openai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAiService {
    RestTemplate restTemplate = new RestTemplate();

    public String getModels() {
        String url = "https://api.openai.com/v1/models";
        RequestEntity<Void> request = RequestEntity.get(URI.create(url)).headers(headers()).build();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response.getBody();
    }

    private HttpHeaders headers() {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");
        String openaiProjectId = System.getenv("OPENAI_PROJECT_ID");
        String openaiOrgId = System.getenv("OPENAI_ORG_ID");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + openaiApiKey);
        httpHeaders.set("OpenAI-Organization", openaiOrgId);
        httpHeaders.set("OpenAI-Project", openaiProjectId);
        return httpHeaders;
    }


}
