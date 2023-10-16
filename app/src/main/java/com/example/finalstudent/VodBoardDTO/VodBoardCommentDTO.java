package com.example.finalstudent.VodBoardDTO;


import com.example.finalstudent.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public class VodBoardCommentDTO {

    private int id; // 댓글의 고유한 인덱스(ID)
    private String vodCmtContent; // 댓글 내용
    private LocalDateTime vodCmtRegdate; // 댓글 작성일자와 시간
    private int vodCmtParentNo; //대댓글을 위한 부모 댓글의 인덱스
    private int vodNo; // 댓글이 속하는 VOD 게시글의 인덱스(ID)
    private UserDTO userDTO; // 댓글 작성자의 회원 인덱스(ID)
    
    private List<VodBoardCommentDTO> vodSonCmtList;    //자식 댓글 리스트

}
