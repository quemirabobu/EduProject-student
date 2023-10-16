package com.example.finalstudent.VodBoardDTO;


import com.example.finalstudent.UserDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VodBoardDTO  implements Serializable { // 보드 dto에 원래 이름, 저장이름, 오브젝트이름

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "VodBoardDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate='" + regDate + '\'' +
                ", modDate='" + modDate + '\'' +
                ", hits=" + hits +
                ", originPath='" + originPath + '\'' +
                ", savePath='" + savePath + '\'' +
                ", objectPath='" + objectPath + '\'' +
                ", originThumb='" + originThumb + '\'' +
                ", saveThumb='" + saveThumb + '\'' +
                ", objectThumb='" + objectThumb + '\'' +
                ", likeCount=" + likeCount +
                ", likeStatus=" + likeStatus +
                ", userDTO=" + userDTO +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(String objectPath) {
        this.objectPath = objectPath;
    }

    public String getOriginThumb() {
        return originThumb;
    }

    public void setOriginThumb(String originThumb) {
        this.originThumb = originThumb;
    }

    public String getSaveThumb() {
        return saveThumb;
    }

    public void setSaveThumb(String saveThumb) {
        this.saveThumb = saveThumb;
    }

    public String getObjectThumb() {
        return objectThumb;
    }

    public void setObjectThumb(String objectThumb) {
        this.objectThumb = objectThumb;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public VodBoardDTO(Integer id, String title, String content, String writer, String regDate, String modDate, int hits, String originPath, String savePath, String objectPath, String originThumb, String saveThumb, String objectThumb, int likeCount, int likeStatus, UserDTO userDTO) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.modDate = modDate;
        this.hits = hits;
        this.originPath = originPath;
        this.savePath = savePath;
        this.objectPath = objectPath;
        this.originThumb = originThumb;
        this.saveThumb = saveThumb;
        this.objectThumb = objectThumb;
        this.likeCount = likeCount;
        this.likeStatus = likeStatus;
        this.userDTO = userDTO;
    }

    private Integer id;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private String modDate;
    private int hits;

    private String originPath;
    private String savePath;
    private String objectPath;

    private String originThumb;
    private String saveThumb;
    private String objectThumb;

    private int likeCount;
    private int likeStatus;

    private UserDTO userDTO;


}
