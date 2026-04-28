package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import com.gying.wander.model.dto.LoginRequest;
import com.gying.wander.model.dto.LoginResponse;
import com.gying.wander.model.dto.UserCreateRequest;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Validated @RequestBody LoginRequest request) {
        return ApiResponse.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ApiResponse<UserEntity> me(@RequestHeader("Authorization") String authorization) {
        return ApiResponse.ok(authService.verifyToken(extractToken(authorization)));
    }

    @GetMapping("/users")
    public ApiResponse<List<UserEntity>> users(@RequestHeader("Authorization") String authorization) {
        UserEntity operator = authService.verifyToken(extractToken(authorization));
        return ApiResponse.ok(authService.listUsers(operator));
    }

    @PostMapping("/users")
    public ApiResponse<String> createUser(@RequestHeader("Authorization") String authorization,
                                          @Validated @RequestBody UserCreateRequest request) {
        UserEntity operator = authService.verifyToken(extractToken(authorization));
        authService.createUser(operator, request);
        return ApiResponse.ok("创建成功");
    }

    private String extractToken(String authorization) {
        if (authorization == null) {
            throw new RuntimeException("缺少Authorization请求头");
        }
        return authorization.replace("Bearer ", "").trim();
    }
}
