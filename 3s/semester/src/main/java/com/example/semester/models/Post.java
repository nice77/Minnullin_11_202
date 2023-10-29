package com.example.semester.models;

import java.util.Date;

@ColumnName(name="posts")
public class Post {
    private Integer id;
    private Integer userId;
    private String header;
    private String body;
    private Date creatingDate;

    public Post(Integer id,
                Integer userId,
                String header,
                String body,
                Date creatingDate) {
        this.id = id;
        this.userId = userId;
        this.header = header;
        this.body = body;
        this.creatingDate = creatingDate;
    }

    public Post() {
        new Post(-1, -1, null, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }
}
