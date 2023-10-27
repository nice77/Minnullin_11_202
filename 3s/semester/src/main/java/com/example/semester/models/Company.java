package com.example.semester.models;

@ColumnName(name="companies")
public class Company {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String hashedPassword;
    private String about;
    private String avatar;

    public Company(Integer id,
                   String name,
                   String phoneNumber,
                   String email,
                   String hashedPassword,
                   String about,
                   String avatar) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.about = about;
        this.avatar = avatar;
    }

    public Company() {
        new Company(-1, null, null, null, null, null, null);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAbout() {
        return about;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
