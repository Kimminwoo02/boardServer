package com.example.boardserver.dto;

import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
public class PostDTO {
    private int id;
    private String name;
    private int isAdmin;
    private String contents;
    private int views;
    private int categoryId;
    private int userId;
    private int fileId;
    private Date updateTime;
    private Date createTime;
    private List<TagDTO> tags;


}
