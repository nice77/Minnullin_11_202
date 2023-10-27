package com.example.semester.models;

@ColumnName(name="comments")
public class Comment {
    private int id;
    private int postId;
    private int userId;
    private String text;

    public Comment(int id,
                   int postId,
                   int userId,
                   String text) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }
}
