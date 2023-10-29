package com.example.semester.models;

@ColumnName(name="comments")
public class Comment {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String text;

    public Comment(Integer id,
                   Integer postId,
                   Integer userId,
                   String text) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.text = text;
    }

    public Comment() {
        new Comment(-1, -1, -1, null);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setUserId(Integer userId) {
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
