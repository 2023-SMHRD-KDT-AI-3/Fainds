package com.example.faindsapplication.Cmt;

public class CmtVO {
    private String cmtWriter;
    private String cmtContent;
    private String cmtCreated_at;


    public CmtVO(String cmtWriter, String cmtContent, String cmtCreated_at) {
        this.cmtWriter = cmtWriter;
        this.cmtContent = cmtContent;
        this.cmtCreated_at = cmtCreated_at;
    }

    public String getCmtWriter() {
        return cmtWriter;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public String getCmtCreated_at() {
        return cmtCreated_at;
    }

}
