package com.example.boardserver.dto;

import lombok.*;

import java.util.Date;


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


    public int getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
