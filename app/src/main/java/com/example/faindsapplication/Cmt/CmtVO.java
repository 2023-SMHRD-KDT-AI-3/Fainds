package com.example.faindsapplication.Cmt;

public class CmtVO {
    private String cmtWriter;
    private String cmtContent;
    //private String cmtCreated_at;
    private int board_seq;


    public CmtVO(String cmtWriter, String cmtContent, int board_seq) {
        this.cmtWriter = cmtWriter;
        this.cmtContent = cmtContent;
        //this.cmtCreated_at = cmtCreated_at;
        this.board_seq = board_seq;
    }

    public String getCmtWriter() {
        return cmtWriter;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    //public String getCmtCreated_at() {
    //    return cmtCreated_at;
    //}

    public int getBoard_seq() {
        return board_seq;
    }

    public CmtVO() {
    }
}
