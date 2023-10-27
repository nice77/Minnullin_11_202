package com.example.semester.models;

import java.util.Date;

@ColumnName(name="posts")
public class Post {
    private int id;
    private int userId;
    private String header;
    private String body;
    private Date creatingDate;

    public Post(int id,
                int userId,
                String header,
                String body,
                Date creatingDate) {
        this.id = id;
        this.userId = userId;
        this.header = header;
        this.body = body;
        this.creatingDate = creatingDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }
}
