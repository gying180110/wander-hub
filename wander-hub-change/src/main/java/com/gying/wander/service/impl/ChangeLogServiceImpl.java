package com.gying.wander.service.impl;

import com.gying.wander.common.AuthzUtil;
import com.gying.wander.model.dto.ChangeLogRequest;
import com.gying.wander.model.entity.ChangeLogEntity;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.repository.ChangeLogRepository;
import com.gying.wander.service.ChangeLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeLogServiceImpl implements ChangeLogService {
    private final ChangeLogRepository changeLogRepository;

    public ChangeLogServiceImpl(ChangeLogRepository changeLogRepository) {
        this.changeLogRepository = changeLogRepository;
    }

    @Override
    public List<ChangeLogEntity> list() {
        return changeLogRepository.listLatest();
    }

    @Override
    public void create(UserEntity operator, ChangeLogRequest request) {
        AuthzUtil.requirePermission(operator, "CHANGELOG_MANAGE");
        changeLogRepository.create(
                request.getVersionNo(),
                request.getModuleName(),
                request.getChangePoint(),
                request.getChangeFile()
        );
    }
}
