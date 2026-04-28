package com.gying.wander.model.entity;

import java.util.Date;

public class ChangeLogEntity {
    private Long id;
    private String versionNo;
    private String moduleName;
    private String changePoint;
    private String changeFile;
    private String gitCommit;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(String changePoint) {
        this.changePoint = changePoint;
    }

    public String getChangeFile() {
        return changeFile;
    }

    public void setChangeFile(String changeFile) {
        this.changeFile = changeFile;
    }

    public String getGitCommit() {
        return gitCommit;
    }

    public void setGitCommit(String gitCommit) {
        this.gitCommit = gitCommit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
