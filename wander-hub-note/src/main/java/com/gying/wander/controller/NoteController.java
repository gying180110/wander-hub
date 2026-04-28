package com.gying.wander.controller;

import com.gying.wander.common.ApiResponse;
import com.gying.wander.model.dto.NoteRequest;
import com.gying.wander.model.entity.NoteEntity;
import com.gying.wander.model.entity.UserEntity;
import com.gying.wander.service.AuthService;
import com.gying.wander.service.NoteService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final AuthService authService;
    private final NoteService noteService;

    public NoteController(AuthService authService, NoteService noteService) {
        this.authService = authService;
        this.noteService = noteService;
    }

    @PostMapping
    public ApiResponse<String> create(@RequestHeader("Authorization") String authorization,
                                      @Validated @RequestBody NoteRequest request) {
        UserEntity user = authService.verifyToken(extractToken(authorization));
        noteService.create(user.getId(), request);
        return ApiResponse.ok("创建成功");
    }

    @GetMapping
    public ApiResponse<List<NoteEntity>> list(@RequestHeader("Authorization") String authorization) {
        UserEntity user = authService.verifyToken(extractToken(authorization));
        return ApiResponse.ok(noteService.list(user.getId()));
    }

    private String extractToken(String authorization) {
        if (authorization == null) {
            throw new RuntimeException("缺少Authorization请求头");
        }
        return authorization.replace("Bearer ", "").trim();
    }
}
