package com.example.semester.models;

import java.util.Date;


@ColumnName(name="vacancies")
public class Vacancy {
    private Integer id;
    private Integer companyId;
    private String header;
    private String body;
    private Date creatingDate;
    private Date meetingDate;
    private String meetingPlace;

    public Vacancy(Integer id,
                   Integer companyId,
                   String header,
                   String body,
                   Date creatingDate,
                   Date meetingDate,
                   String meetingPlace) {
        this.id = id;
        this.companyId = companyId;
        this.header = header;
        this.body = body;
        this.creatingDate = creatingDate;
        this.meetingDate = meetingDate;
        this.meetingPlace = meetingPlace;
    }

    public Vacancy() {
        new Vacancy(-1, -1, null, null, new Date(), null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }
}
