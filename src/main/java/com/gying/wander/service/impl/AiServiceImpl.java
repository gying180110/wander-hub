package com.gying.wander.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gying.wander.config.properties.AiProperties;
import com.gying.wander.service.AiService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AiServiceImpl implements AiService {
    private final AiProperties aiProperties;
    private final RestTemplate restTemplate;
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    public AiServiceImpl(AiProperties aiProperties,
                         RestTemplate restTemplate,
                         RestHighLevelClient restHighLevelClient,
                         ObjectMapper objectMapper) {
        this.aiProperties = aiProperties;
        this.restTemplate = restTemplate;
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public String chat(Long userId, String prompt) {
        String answer;
        if (!StringUtils.hasText(aiProperties.getBaseUrl())) {
            answer = "模拟AI回复: " + prompt;
        } else {
            answer = callRealModel(prompt);
        }
        saveAiLog(userId, prompt, answer);
        return answer;
    }

    private String callRealModel(String prompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (StringUtils.hasText(aiProperties.getApiKey())) {
                headers.setBearerAuth(aiProperties.getApiKey());
            }

            Map<String, Object> body = new HashMap<String, Object>();
            body.put("model", aiProperties.getModel());

            Map<String, String> message = new HashMap<String, String>();
            message.put("role", "user");
            message.put("content", prompt);
            body.put("messages", Collections.singletonList(message));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(body, headers);
            String responseText = restTemplate.postForObject(aiProperties.getBaseUrl(), entity, String.class);
            JsonNode jsonNode = objectMapper.readTree(responseText);
            JsonNode contentNode = jsonNode.path("choices").path(0).path("message").path("content");
            return contentNode.isMissingNode() ? responseText : contentNode.asText();
        } catch (Exception ex) {
            return "AI调用失败: " + ex.getMessage();
        }
    }

    private void saveAiLog(Long userId, String prompt, String answer) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("prompt", prompt);
            map.put("answer", answer);
            map.put("timestamp", System.currentTimeMillis());
            IndexRequest indexRequest = new IndexRequest("wander_ai_log").source(objectMapper.convertValue(map, Map.class));
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception ignored) {
        }
    }
}
