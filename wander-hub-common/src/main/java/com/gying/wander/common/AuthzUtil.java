package com.gying.wander.common;

import com.gying.wander.model.entity.UserEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthzUtil {
    private AuthzUtil() {
    }

    public static boolean isAdmin(UserEntity user) {
        return user != null && "ADMIN".equalsIgnoreCase(user.getRoleCode());
    }

    public static void requireAdmin(UserEntity user) {
        if (!isAdmin(user)) {
            throw new RuntimeException("无管理员权限");
        }
    }

    public static void requirePermission(UserEntity user, String permission) {
        if (isAdmin(user)) {
            return;
        }
        if (user.getPermissions() == null || user.getPermissions().trim().isEmpty()) {
            throw new RuntimeException("缺少权限: " + permission);
        }
        Set<String> permissionSet = new HashSet<String>(Arrays.asList(user.getPermissions().split(",")));
        if (!permissionSet.contains(permission)) {
            throw new RuntimeException("缺少权限: " + permission);
        }
    }
}
