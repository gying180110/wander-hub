package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import com.gying.wander.model.dto.LoginRequest;
import com.gying.wander.model.dto.LoginResponse;
import com.gying.wander.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
