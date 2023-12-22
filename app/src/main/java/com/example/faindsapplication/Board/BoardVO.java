package com.example.faindsapplication.Board;

import java.util.Date;

public class BoardVO {
    private String boardTitle;
    private String boardContent;
    private Date created_at;
    private int cmtSeq;
    private String cmtContent;

    public BoardVO(String boardTitle, String boardContent, Date created_at, int cmtSeq, String cmtContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.created_at = created_at;
        this.cmtSeq = cmtSeq;
        this.cmtContent = cmtContent;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public int getCmtSeq() {
        return cmtSeq;
    }

    public String getCmtContent() {
        return cmtContent;
    }
}
