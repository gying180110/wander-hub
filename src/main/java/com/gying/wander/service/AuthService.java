package com.gying.wander.service;

import com.gying.wander.model.dto.LoginRequest;
import com.gying.wander.model.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    Long verifyToken(String token);
}
