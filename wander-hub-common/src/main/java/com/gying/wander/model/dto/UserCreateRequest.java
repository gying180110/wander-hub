package com.gying.wander.model.dto;

import javax.validation.constraints.NotBlank;

public class UserCreateRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String roleCode;
    private String permissions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
