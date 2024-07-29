package com.example.springbootopenai.openai.transcription;

import com.example.springbootopenai.openai.HeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class TranslationService {
    private final HeaderService headerService;
    private RestTemplate restTemplate = new RestTemplate();

    private FileSystemResource theFile(String path) {
        File f = new File(path);
        return new FileSystemResource(f);
    }

    //
//    curl --request POST \
//            --url https://api.openai.com/v1/audio/translations \
//            --header "Authorization: Bearer $OPENAI_API_KEY" \
//            --header 'Content-Type: multipart/form-data' \
//            --form file=@/path/to/file/german.mp3 \
//            --form model=whisper-1
    public String doTranslationOfFile(String filepath) {
        String url = "https://api.openai.com/v1/audio/translations";
        MultiValueMap<String, Object> dataToPost = new LinkedMultiValueMap<>();
        dataToPost.add("model", "whisper-1");
        dataToPost.add("file", theFile(filepath));
        dataToPost.add("response_format", "json");
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(dataToPost, headerService.headersMultipartFormData());
        log.info("Send file to transcribe");
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        return response.getBody();
    }
}
