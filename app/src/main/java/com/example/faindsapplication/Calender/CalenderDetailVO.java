package com.example.faindsapplication.Calender;

public class CalenderDetailVO {
    private String startedAt; // 시작 시간
    private String endedAt; // 종료 시간
    private String workPay; // 시급
    private String workDay; // 근무 날짜

    private int workSeq;

    public CalenderDetailVO(String startedAt, String endedAt, String workPay, String workDay, int workSeq) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.workPay = workPay;
        this.workDay = workDay;
        this.workSeq = workSeq;
    }
    // getter 메서드
    public String getStartedAt() {
        return startedAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public String getWorkPay() {
        return workPay;
    }

    public String getWorkDay() {
        return workDay;
    }

    public int getWorkSeq() {
        return workSeq;
    }
}
