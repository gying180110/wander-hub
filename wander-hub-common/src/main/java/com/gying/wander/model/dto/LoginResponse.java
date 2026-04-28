package com.gying.wander.model.dto;

public class LoginResponse {
    private String token;
    private String username;
    private String nickname;
    private String roleCode;
    private String permissions;

    public LoginResponse() {
    }

    public LoginResponse(String token, String username, String nickname, String roleCode, String permissions) {
        this.token = token;
        this.username = username;
        this.nickname = nickname;
        this.roleCode = roleCode;
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
