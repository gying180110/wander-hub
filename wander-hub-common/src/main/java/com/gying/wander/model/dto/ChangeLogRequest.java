package com.gying.wander.model.dto;

import javax.validation.constraints.NotBlank;

public class ChangeLogRequest {
    @NotBlank
    private String versionNo;
    @NotBlank
    private String moduleName;
    @NotBlank
    private String changePoint;
    @NotBlank
    private String changeFile;

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
}
