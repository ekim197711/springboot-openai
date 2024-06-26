package com.example.springbootopenai.openai.audio;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAiServiceTTS {
    RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders headersTTS() {
        String openaiApiKey = System.getenv("OPENAI_API_KEY");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + openaiApiKey);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return httpHeaders;
    }

    @SneakyThrows
    public void tts(VoiceType voiceType, String text, String dest) {
        String url = "https://api.openai.com/v1/audio/speech";
        Map<String, Object> dataToPost = new HashMap<>();
        dataToPost.put("model", "tts-1");
        dataToPost.put("input", text);
        dataToPost.put("voice", voiceType.name());
        String data = new ObjectMapper().writeValueAsString(dataToPost);
        System.out.println("Send data " + data);
        restTemplate.execute(url, HttpMethod.POST,
                clientHttpRequest -> {
                    clientHttpRequest.getHeaders().addAll(headersTTS());
                    clientHttpRequest.getBody().write(data.getBytes(StandardCharsets.UTF_8));
                }
                , clientHttpResponse -> {
                    String dir = "C:\\Users\\mikem\\projects\\springboot-openai\\";
                    System.out.println("Download to " + dir);
                    File dlfile = File.createTempFile(dest + "_" + voiceType.name() + "_", ".mp3", new File(dir));
                    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(dlfile, true));
                    return dlfile;
                });
    }


}
