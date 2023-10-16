package com.example.finalstudent;

import java.io.Serializable;

public class DriverPhoto implements Serializable {


    public DriverPhoto(Long id, int carnumber, String photoname) {
        this.id = id;
        this.carnumber = carnumber;
        this.photoname = photoname;
    }

    private Long id;
    private int carnumber;
    private String photoname;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DriverPhoto{" +
                "id=" + id +
                ", carnumber=" + carnumber +
                ", photoname='" + photoname + '\'' +
                '}';
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

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }
}
