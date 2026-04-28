package com.gying.wander.service.impl;

import com.gying.wander.common.AuthzUtil;
import com.gying.wander.model.dto.AnnouncementRequest;
import com.gying.wander.model.entity.AnnouncementEntity;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.repository.AnnouncementRepository;
import com.gying.wander.service.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<AnnouncementEntity> listActive() {
        return announcementRepository.listActive();
    }

    @Override
    public List<AnnouncementEntity> listAll(UserEntity operator) {
        AuthzUtil.requirePermission(operator, "ANNOUNCEMENT_MANAGE");
        return announcementRepository.listAll();
    }

    @Override
    public void create(UserEntity operator, AnnouncementRequest request) {
        AuthzUtil.requirePermission(operator, "ANNOUNCEMENT_MANAGE");
        announcementRepository.create(
                request.getTitle(),
                request.getContent(),
                request.getActive(),
                request.getPinned(),
                request.getPlaySeconds(),
                request.getExpireTime(),
                request.getSortNo()
        );
    }
}
