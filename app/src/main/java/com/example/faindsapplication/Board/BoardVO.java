package com.example.faindsapplication.Board;

import java.util.Date;

public class BoardVO {
    private String boardTitle;
    private String boardContent;
    // private Date created_at;
    private String boardWriter;
    private int boardCmtNum; // 댓글 수
    // private int boardSeq;

    public BoardVO(String boardTitle, String boardContent, String boardWriter, int boardCmtNum) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
        this.boardCmtNum = boardCmtNum;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public int getBoardCmtNum() {
        return boardCmtNum;
    }
}
