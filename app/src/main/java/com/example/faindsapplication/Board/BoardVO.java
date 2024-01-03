package com.example.faindsapplication.Board;

import java.util.Date;

public class BoardVO {
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private String created_at; // 게시글 작성일자
    private String boardWriter; // 게시글 작성자
    private int boardCmtNum; // 댓글 수
    private int boardSeq; // 게시글 번호

    // 기본 생성자
    public BoardVO() {
    }

    // 매개변수를 받는 생성자
    public BoardVO(String boardTitle, String boardContent, String created_at, String boardWriter, int boardCmtNum, int boardSeq) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.created_at = created_at;
        this.boardWriter = boardWriter;
        this.boardCmtNum = boardCmtNum;
        this.boardSeq = boardSeq;
    }

    // getter 메서드
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
