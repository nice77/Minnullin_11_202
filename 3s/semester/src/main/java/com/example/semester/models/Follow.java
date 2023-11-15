package com.example.semester.models;


@ColumnName(name="follows")
public class Follow {
    private int id;
    private int author;
    private int follower;

    public Follow(int id, int author, int followee) {
        this.id = id;
        this.author = author;
        this.follower = followee;
    }

    public Follow() {
        new Follow(-1, -1, -1);
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }
}
