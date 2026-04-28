package com.gying.wander.model.entity;

import java.util.Date;

public class AnnouncementEntity {
    private Long id;
    private String title;
    private String content;
    private Integer active;
    private Integer playSeconds;
    private Integer sortNo;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
