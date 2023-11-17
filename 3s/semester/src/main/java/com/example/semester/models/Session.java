package com.example.semester.models;

@ColumnName(name="sessions")
public class Session {

    private int id;
    private int userId;
    private String token;
    private String device;
    private String userType;

    public Session(int id, int userId, String token, String device, String userType) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.device = device;
        this.userType = userType;
    }

    public Session() {
        new Session(-1, -1, null, null, null);
    }

    public int getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
