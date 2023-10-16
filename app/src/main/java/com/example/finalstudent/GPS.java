package com.example.finalstudent;

import java.io.Serializable;

public class GPS implements Serializable {


    @Override
    public String toString() {
        return "GPS{" +
                "id=" + id +
                ", carnumber=" + carnumber +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", time='" + time + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(int carnumber) {
        this.carnumber = carnumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public GPS(Long id, int carnumber, double latitude, double longitude, String time, String phonenumber) {
        this.id = id;
        this.carnumber = carnumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.phonenumber = phonenumber;
    }

    private Long id;

    private int carnumber;

    private double latitude;

    private double longitude;
    private String time;
    private String phonenumber;







}
