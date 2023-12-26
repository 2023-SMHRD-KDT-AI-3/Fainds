package com.example.faindsapplication.Board;

import java.util.Date;

public class BoardVO {
    private String boardTitle;
    private String boardContent;
    //private Date created_at;
    private int cmtSeq;
    private String cmtContent;

    private String boardCmtNum;

    public BoardVO(String boardTitle, String boardContent, int cmtSeq, String cmtContent,String boardCmtNum) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        //this.created_at = created_at;
        this.cmtSeq = cmtSeq;
        this.cmtContent = cmtContent;
        this.boardCmtNum = boardCmtNum;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

//    public Date getCreated_at() {
//        return created_at;
//    }

    public int getCmtSeq() {
        return cmtSeq;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public String getBoardCmtNum() {
        return boardCmtNum;
    }
}
