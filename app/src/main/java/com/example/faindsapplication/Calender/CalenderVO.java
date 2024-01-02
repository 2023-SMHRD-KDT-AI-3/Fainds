package com.example.faindsapplication.Calender;

public class CalenderVO {
    private String date;
    private String dailySalary;

    public CalenderVO(String date, String dailySalary) {
        this.date = date;
        this.dailySalary = dailySalary;
    }

    public CalenderVO() {
    }

    public String getDate() {
        return date;
    }

    public String getDailySalary() {
        return dailySalary;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDailySalary(String dailySalary) {
        this.dailySalary = dailySalary;
    }
}
