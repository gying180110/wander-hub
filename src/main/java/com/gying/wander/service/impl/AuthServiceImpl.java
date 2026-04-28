package com.gying.wander.service.impl;

import com.gying.wander.model.dto.LoginRequest;
import com.gying.wander.model.dto.LoginResponse;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.repository.UserRepository;
import com.gying.wander.service.AuthService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String TOKEN_PREFIX = "wander:token:";
    private final ConcurrentHashMap<String, String> localTokenStore = new ConcurrentHashMap<String, String>();
    private final UserRepository userRepository;
    private final StringRedisTemplate stringRedisTemplate;

    public AuthServiceImpl(UserRepository userRepository, StringRedisTemplate stringRedisTemplate) {
        this.userRepository = userRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        try {
            stringRedisTemplate.opsForValue().set(TOKEN_PREFIX + token, String.valueOf(user.getId()), 2, TimeUnit.HOURS);
        } catch (Exception ex) {
            localTokenStore.put(token, String.valueOf(user.getId()));
        }
        return new LoginResponse(token, user.getUsername(), user.getNickname());
    }

    @Override
    public Long verifyToken(String token) {
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("缺少登录凭证");
        }
        String userId;
        try {
            userId = stringRedisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        } catch (Exception ex) {
            userId = localTokenStore.get(token);
        }
        if (!StringUtils.hasText(userId)) {
            throw new RuntimeException("登录已过期，请重新登录");
        }
        return Long.valueOf(userId);
    }
}
