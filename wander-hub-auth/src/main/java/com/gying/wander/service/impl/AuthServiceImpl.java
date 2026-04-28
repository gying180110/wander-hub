package com.gying.wander.service.impl;

import com.gying.wander.model.dto.LoginRequest;
import com.gying.wander.model.dto.LoginResponse;
import com.gying.wander.model.dto.UserCreateRequest;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.repository.UserRepository;
import com.gying.wander.service.AuthService;
import com.gying.wander.common.AuthzUtil;
import com.gying.wander.common.RoleTemplates;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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
        String userSnapshot = user.getId() + "|" + user.getRoleCode() + "|" + user.getPermissions();
        try {
            stringRedisTemplate.opsForValue().set(TOKEN_PREFIX + token, userSnapshot, 2, TimeUnit.HOURS);
        } catch (Exception ex) {
            localTokenStore.put(token, userSnapshot);
        }
        return new LoginResponse(token, user.getUsername(), user.getNickname(), user.getRoleCode(), user.getPermissions());
    }

    @Override
    public UserEntity verifyToken(String token) {
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("缺少登录凭证");
        }
        String snapshot;
        try {
            snapshot = stringRedisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        } catch (Exception ex) {
            snapshot = localTokenStore.get(token);
        }
        if (!StringUtils.hasText(snapshot)) {
            throw new RuntimeException("登录已过期，请重新登录");
        }
        Long userId = Long.valueOf(snapshot.split("\\|")[0]);
        UserEntity user = userRepository.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    @Override
    public List<UserEntity> listUsers(UserEntity operator) {
        AuthzUtil.requireAdmin(operator);
        return userRepository.listAll();
    }

    @Override
    public void createUser(UserEntity operator, UserCreateRequest request) {
        AuthzUtil.requireAdmin(operator);
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        String permissions = StringUtils.hasText(request.getPermissions())
                ? request.getPermissions()
                : RoleTemplates.defaultPermissions(request.getRoleCode());
        userRepository.createUser(
                request.getUsername(),
                request.getPassword(),
                request.getNickname(),
                request.getRoleCode(),
                permissions
        );
    }
}
