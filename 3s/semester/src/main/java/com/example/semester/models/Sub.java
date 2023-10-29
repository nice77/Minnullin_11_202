package com.example.semester.models;


@ColumnName(name="subs")
public class Sub {
    private Integer id;
    private Integer vacancyId;
    private Integer userId;

    public Sub(Integer id, Integer vacancyId, Integer userId) {
        this.id = id;
        this.vacancyId = vacancyId;
        this.userId = userId;
    }

    public Sub() {
        new Sub(null, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
