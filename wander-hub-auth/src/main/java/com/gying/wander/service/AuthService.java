package com.gying.wander.service;

import com.gying.wander.model.dto.LoginRequest;
import com.gying.wander.model.dto.LoginResponse;
import com.gying.wander.model.dto.UserCreateRequest;
import com.gying.wander.model.entity.UserEntity;

import java.util.List;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    UserEntity verifyToken(String token);

    List<UserEntity> listUsers(UserEntity operator);

    void createUser(UserEntity operator, UserCreateRequest request);
}
