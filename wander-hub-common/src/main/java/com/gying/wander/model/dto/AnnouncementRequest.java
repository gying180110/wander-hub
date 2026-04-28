package com.gying.wander.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

public class AnnouncementRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Integer active;
    @NotNull
    private Integer pinned;
    @NotNull
    private Integer playSeconds;
    @NotNull
    private Integer sortNo;
    private Date expireTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getPlaySeconds() {
        return playSeconds;
    }

    public void setPlaySeconds(Integer playSeconds) {
        this.playSeconds = playSeconds;
    }

    public Integer getPinned() {
        return pinned;
    }

    public void setPinned(Integer pinned) {
        this.pinned = pinned;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
