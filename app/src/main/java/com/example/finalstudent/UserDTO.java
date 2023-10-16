package com.example.finalstudent;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDTO  implements Serializable {

    private Integer id;
    private String userId;
    private String userPw;
    private String userName;
    //    private String userEmail;
    private String userTel;
    private String userRegdate;
    private String role;
    private String curUserPw;
    private String token;
    private String userType;
    private String userBirth;
    private String userSchool;
    private String userAddress;
    private Integer userJoinId;
    private String userAddressDetail;
    private String userConsultContent;

    private CourseDTO courseDTO;
    private String approval;

    private String userSpecialNote;

    private Integer userBus;
    private Integer userScore;


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userRegdate='" + userRegdate + '\'' +
                ", role='" + role + '\'' +
                ", curUserPw='" + curUserPw + '\'' +
                ", token='" + token + '\'' +
                ", userType='" + userType + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userSchool='" + userSchool + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userJoinId=" + userJoinId +
                ", userAddressDetail='" + userAddressDetail + '\'' +
                ", userConsultContent='" + userConsultContent + '\'' +
                ", courseDTO=" + courseDTO +
                ", approval='" + approval + '\'' +
                ", userSpecialNote='" + userSpecialNote + '\'' +
                ", userBus=" + userBus +
                ", userScore=" + userScore +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserRegdate() {
        return userRegdate;
    }

    public void setUserRegdate(String userRegdate) {
        this.userRegdate = userRegdate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCurUserPw() {
        return curUserPw;
    }

    public void setCurUserPw(String curUserPw) {
        this.curUserPw = curUserPw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserJoinId() {
        return userJoinId;
    }

    public void setUserJoinId(Integer userJoinId) {
        this.userJoinId = userJoinId;
    }

    public String getUserAddressDetail() {
        return userAddressDetail;
    }

    public void setUserAddressDetail(String userAddressDetail) {
        this.userAddressDetail = userAddressDetail;
    }

    public String getUserConsultContent() {
        return userConsultContent;
    }

    public void setUserConsultContent(String userConsultContent) {
        this.userConsultContent = userConsultContent;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getUserSpecialNote() {
        return userSpecialNote;
    }

    public void setUserSpecialNote(String userSpecialNote) {
        this.userSpecialNote = userSpecialNote;
    }

    public Integer getUserBus() {
        return userBus;
    }

    public void setUserBus(Integer userBus) {
        this.userBus = userBus;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public UserDTO(Integer id, String userId, String userPw, String userName, String userTel, String userRegdate, String role, String curUserPw, String token, String userType, String userBirth, String userSchool, String userAddress, Integer userJoinId, String userAddressDetail, String userConsultContent, CourseDTO courseDTO, String approval, String userSpecialNote, Integer userBus, Integer userScore) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userTel = userTel;
        this.userRegdate = userRegdate;
        this.role = role;
        this.curUserPw = curUserPw;
        this.token = token;
        this.userType = userType;
        this.userBirth = userBirth;
        this.userSchool = userSchool;
        this.userAddress = userAddress;
        this.userJoinId = userJoinId;
        this.userAddressDetail = userAddressDetail;
        this.userConsultContent = userConsultContent;
        this.courseDTO = courseDTO;
        this.approval = approval;
        this.userSpecialNote = userSpecialNote;
        this.userBus = userBus;
        this.userScore = userScore;
    }
}
