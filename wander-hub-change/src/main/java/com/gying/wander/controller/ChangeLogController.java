package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import com.gying.wander.model.dto.ChangeLogRequest;
import com.gying.wander.model.entity.ChangeLogEntity;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.service.AuthService;
import com.gying.wander.service.ChangeLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/changelog")
public class ChangeLogController {
    private final ChangeLogService changeLogService;
    private final AuthService authService;

    public ChangeLogController(ChangeLogService changeLogService, AuthService authService) {
        this.changeLogService = changeLogService;
        this.authService = authService;
    }

    @GetMapping
    public ApiResponse<List<ChangeLogEntity>> list() {
        return ApiResponse.ok(changeLogService.list());
    }

    @PostMapping
    public ApiResponse<String> create(@RequestHeader("Authorization") String authorization,
                                      @Validated @RequestBody ChangeLogRequest request) {
        UserEntity operator = authService.verifyToken(extractToken(authorization));
        changeLogService.create(operator, request);
        return ApiResponse.ok("记录成功");
    }

    private String extractToken(String authorization) {
        if (authorization == null) {
            throw new RuntimeException("缺少Authorization请求头");
        }
        return authorization.replace("Bearer ", "").trim();
    }
}
