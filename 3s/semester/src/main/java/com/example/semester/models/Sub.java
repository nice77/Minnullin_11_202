package com.example.semester.models;


@ColumnName(name="subs")
public class Sub {
    private int vacancyId;
    private int userId;

    public Sub(int vacancyId, int userId) {
        this.vacancyId = vacancyId;
        this.userId = userId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public int getUserId() {
        return userId;
    }
}
