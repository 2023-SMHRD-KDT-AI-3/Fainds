package com.example.faindsapplication.Calender;

public class CalenderDetailVO {
    private String startedAt;
    private String endedAt;
    private String workPay;
    private String workDay;

    public CalenderDetailVO(String startedAt, String endedAt, String workPay, String workDay) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.workPay = workPay;
        this.workDay = workDay;
    }

    public CalenderDetailVO() {
    }

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
}