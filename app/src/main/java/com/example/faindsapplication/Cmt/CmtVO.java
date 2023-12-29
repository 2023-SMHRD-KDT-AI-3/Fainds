package com.example.faindsapplication.Cmt;

public class CmtVO {
    private String cmtWriter;
    private String cmtContent;

    private String createdAt;

    public CmtVO(String cmtWriter, String cmtContent, String createdAt) {
        this.cmtWriter = cmtWriter;
        this.cmtContent = cmtContent;
        this.createdAt = createdAt;
    }

    public String getCmtWriter() {
        return cmtWriter;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
