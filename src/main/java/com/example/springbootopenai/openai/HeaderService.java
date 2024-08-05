package com.example.springbootopenai.openai;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class HeaderService {

    public HttpHeaders headersTTS() {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + openaiApiKey);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return httpHeaders;
    }

    public HttpHeaders headersImageGeneration() {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + openaiApiKey);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return httpHeaders;
    }

    public HttpHeaders headersMultipartFormData() {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + openaiApiKey);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA.toString());
        return httpHeaders;
    }
}
