package com.gying.wander.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gying.wander.model.dto.NoteRequest;
import com.gying.wander.model.entity.NoteEntity;
import com.gying.wander.repository.NoteRepository;
import com.gying.wander.service.NoteService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    public NoteServiceImpl(NoteRepository noteRepository,
                           RestHighLevelClient restHighLevelClient,
                           ObjectMapper objectMapper) {
        this.noteRepository = noteRepository;
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void create(Long userId, NoteRequest request) {
        noteRepository.save(userId, request.getTitle(), request.getContent());
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("title", request.getTitle());
            map.put("content", request.getContent());
            IndexRequest indexRequest = new IndexRequest("wander_note").source(objectMapper.convertValue(map, Map.class));
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception ignored) {
        }
    }

    @Override
    public List<NoteEntity> list(Long userId) {
        return noteRepository.listByUserId(userId);
    }
}
