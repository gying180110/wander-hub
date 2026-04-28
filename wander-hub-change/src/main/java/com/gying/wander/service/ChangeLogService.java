package com.gying.wander.service;

import com.gying.wander.model.dto.ChangeLogRequest;
import com.gying.wander.model.entity.ChangeLogEntity;
import com.gying.wander.model.entity.UserEntity;

import java.util.List;

public interface ChangeLogService {
    List<ChangeLogEntity> list();

    void create(UserEntity operator, ChangeLogRequest request);
}
