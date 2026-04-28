package com.gying.wander.common;

public class RoleTemplates {
    public static final String ADMIN = "ADMIN";
    public static final String EDITOR = "EDITOR";
    public static final String VIEWER = "VIEWER";
    public static final String USER = "USER";

    private RoleTemplates() {
    }

    public static String defaultPermissions(String roleCode) {
        if (roleCode == null) {
            return "";
        }
        String role = roleCode.toUpperCase();
        if (ADMIN.equals(role)) {
            return String.join(",",
                    PermissionCodes.USER_MANAGE,
                    PermissionCodes.AI_CHAT,
                    PermissionCodes.ANNOUNCEMENT_MANAGE,
                    PermissionCodes.CHANGELOG_MANAGE
            );
        }
        if (EDITOR.equals(role)) {
            return String.join(",",
                    PermissionCodes.AI_CHAT,
                    PermissionCodes.ANNOUNCEMENT_MANAGE,
                    PermissionCodes.CHANGELOG_MANAGE
            );
        }
        if (VIEWER.equals(role)) {
            return PermissionCodes.AI_CHAT;
        }
        if (USER.equals(role)) {
            return PermissionCodes.AI_CHAT;
        }
        return "";
    }
}
