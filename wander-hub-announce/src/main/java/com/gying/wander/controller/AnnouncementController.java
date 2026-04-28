package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import com.gying.wander.model.dto.AnnouncementRequest;
import com.gying.wander.model.entity.AnnouncementEntity;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.service.AnnouncementService;
import com.gying.wander.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final AuthService authService;

    public AnnouncementController(AnnouncementService announcementService, AuthService authService) {
        this.announcementService = announcementService;
        this.authService = authService;
    }

    @GetMapping("/play")
    public ApiResponse<List<AnnouncementEntity>> playList() {
        return ApiResponse.ok(announcementService.listActive());
    }

    @GetMapping
    public ApiResponse<List<AnnouncementEntity>> list(@RequestHeader("Authorization") String authorization) {
        UserEntity operator = authService.verifyToken(extractToken(authorization));
        return ApiResponse.ok(announcementService.listAll(operator));
    }

    @PostMapping
    public ApiResponse<String> create(@RequestHeader("Authorization") String authorization,
                                      @Validated @RequestBody AnnouncementRequest request) {
        UserEntity operator = authService.verifyToken(extractToken(authorization));
        announcementService.create(operator, request);
        return ApiResponse.ok("发布成功");
    }

    private String extractToken(String authorization) {
        if (authorization == null) {
            throw new RuntimeException("缺少Authorization请求头");
        }
        return authorization.replace("Bearer ", "").trim();
    }
}
