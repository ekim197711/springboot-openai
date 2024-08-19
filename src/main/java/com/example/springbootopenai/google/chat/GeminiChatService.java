package com.example.springbootopenai.google.chat;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class GeminiChatService {

    private static String PROJECT_ID = "mikes-demo2023";
    private static String LOCATION = "europe-west4";
    private static String MODEL_ID = "gemini-1.5-flash-001";

    private final static URI URL = URI.create(String.format("https://%s-aiplatform.googleapis.com/v1/projects/%s/locations/%s/publishers/google/models/%s:generateContent",LOCATION, PROJECT_ID, LOCATION, MODEL_ID));
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    public String doPrompt(String prompt){
        Map<Object, Object> dataToPost = requestBody(prompt);
        log.info("Url to post to {} Data to post {}",URL, dataToPost);
        RequestEntity<Map<Object, Object>> request = RequestEntity.post(URL).headers(headers())
                .body(requestBody(prompt));
        String response = REST_TEMPLATE.exchange(request, String.class).getBody();
        log.info("Response: " + response);
        return response;
    }

//    "contents": [{
//        "role": "user",
//                "parts": [{
//            "text": "TEXT"
//        }]
//    }]
    private org.springframework.http.HttpHeaders headers(){
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.getApplicationDefault();
            credentials.refreshIfExpired();
            AccessToken token = credentials.getAccessToken();
            log.info("Token: {}", token);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization","Bearer " + token.getTokenValue());
            httpHeaders.add("Content-Type","application/json; charset=utf-8");
            return httpHeaders;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Map<Object, Object> requestBody(String prompt){
        Map<Object, Object> body = new HashMap<>();
        body.put("contents", List.of(
                Map.of("role", "user",
                        "parts", List.of(Map.of("text", prompt))
                )
        )
        );

        return body;
    }
}
