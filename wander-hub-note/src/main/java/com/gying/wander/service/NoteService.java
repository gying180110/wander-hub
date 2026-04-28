package com.gying.wander.service;

import com.gying.wander.model.dto.NoteRequest;
import com.gying.wander.model.entity.NoteEntity;

import java.util.List;

public interface NoteService {
    void create(Long userId, NoteRequest request);

    List<NoteEntity> list(Long userId);
}
