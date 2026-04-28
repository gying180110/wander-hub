package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public ApiResponse<Map<String, String>> health() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "UP");
        map.put("service", "wander-hub");
        return ApiResponse.ok(map);
    }
}
