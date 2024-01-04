package com.example.faindsapplication.Cmt;

public class CmtVO {
    private String cmtWriter; // 댓글 작성자
    private String cmtContent; // 댓글 내용
    private String createdAt; // 댓글 작성 시간

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
