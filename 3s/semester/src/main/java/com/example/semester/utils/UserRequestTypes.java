package com.example.semester.utils;

public enum UserRequestTypes {
    UNSUB("unsub"),
    FOLLOWERS("followers"),
    AUTHORS("authors");

    private final String type;
    UserRequestTypes(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
