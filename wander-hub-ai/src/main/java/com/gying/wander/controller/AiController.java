package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import com.gying.wander.common.AuthzUtil;
import com.gying.wander.model.dto.AiChatRequest;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.service.AiService;
import com.gying.wander.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {
    private final AuthService authService;
    private final AiService aiService;

    public AiController(AuthService authService, AiService aiService) {
        this.authService = authService;
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public ApiResponse<Map<String, String>> chat(@RequestHeader("Authorization") String authorization,
                                                 @Validated @RequestBody AiChatRequest request) {
        UserEntity user = authService.verifyToken(extractToken(authorization));
        AuthzUtil.requirePermission(user, "AI_CHAT");
        String answer = aiService.chat(user.getId(), request.getPrompt());
        Map<String, String> map = new HashMap<String, String>();
        map.put("answer", answer);
        return ApiResponse.ok(map);
    }

    private String extractToken(String authorization) {
        if (authorization == null) {
            throw new RuntimeException("缺少Authorization请求头");
        }
        return authorization.replace("Bearer ", "").trim();
    }
}
