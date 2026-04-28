package com.gying.wander.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnnouncementRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Integer active;
    @NotNull
    private Integer playSeconds;
    @NotNull
    private Integer sortNo;

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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
