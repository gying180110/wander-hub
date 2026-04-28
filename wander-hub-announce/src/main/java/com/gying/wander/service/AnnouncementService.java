package com.gying.wander.service;

import com.gying.wander.model.dto.AnnouncementRequest;
import com.gying.wander.model.entity.AnnouncementEntity;
import com.gying.wander.model.entity.UserEntity;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementEntity> listActive();

    List<AnnouncementEntity> listAll(UserEntity operator);

    void create(UserEntity operator, AnnouncementRequest request);
}
