package com.example.semester.models;


@ColumnName(name="users")
public class User {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String email;
    private String hashedPassword;
    private String about;
    private String avatar;

    public User(Integer id,
                String name,
                String surname,
                String patronymic,
                String phoneNumber,
                String email,
                String hashed_password,
                String about,
                String avatar) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hashedPassword = hashed_password;
        this.about = about;
        this.avatar = avatar;
    }

    public User() {
        new User(-1, null, null, null, null, null, null, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.surname;
    }
}
