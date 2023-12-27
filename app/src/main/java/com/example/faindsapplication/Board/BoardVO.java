package com.example.faindsapplication.Board;

import java.util.Date;

public class BoardVO {
    private String boardTitle;
    private String boardContent;
    private String created_at;
    private String boardWriter;
    private int boardCmtNum; // 댓글 수
     private int boardSeq;




    public BoardVO() {

    }

    public BoardVO(String boardTitle, String boardContent, String created_at, String boardWriter, int boardCmtNum, int boardSeq) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.created_at = created_at;
        this.boardWriter = boardWriter;
        this.boardCmtNum = boardCmtNum;
        this.boardSeq = boardSeq;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public int getBoardCmtNum() {
        return boardCmtNum;
    }

    public int getBoardSeq() {
        return boardSeq;
    }
}
