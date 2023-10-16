package com.example.finalstudent;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendDTO implements Serializable {

    private int id;
    private String attStart;
    private String attFinish;
    private String attDate;
    private String attContent;

    private int userNo;

    @Override
    public String toString() {
        return "AttendDTO{" +
                "id=" + id +
                ", attStart='" + attStart + '\'' +
                ", attFinish='" + attFinish + '\'' +
                ", attDate='" + attDate + '\'' +
                ", attContent='" + attContent + '\'' +
                ", userNo=" + userNo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttStart() {
        return attStart;
    }

    public void setAttStart(String attStart) {
        this.attStart = attStart;
    }

    public String getAttFinish() {
        return attFinish;
    }

    public void setAttFinish(String attFinish) {
        this.attFinish = attFinish;
    }

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }

    public String getAttContent() {
        return attContent;
    }

    public void setAttContent(String attContent) {
        this.attContent = attContent;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public AttendDTO(int id, String attStart, String attFinish, String attDate, String attContent, int userNo) {
        this.id = id;
        this.attStart = attStart;
        this.attFinish = attFinish;
        this.attDate = attDate;
        this.attContent = attContent;
        this.userNo = userNo;
    }
}
