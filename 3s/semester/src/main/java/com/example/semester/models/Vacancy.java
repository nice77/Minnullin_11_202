package com.example.semester.models;

import java.util.Date;


@ColumnName(name="vacancy")
public class Vacancy {
    private int id;
    private int companyId;
    private String header;
    private String body;
    private Date creatingDate;
    private Date meetingDate;
    private Date meetingPlace;

    public Vacancy(int id,
                   int companyId,
                   String header,
                   String body,
                   Date creatingDate,
                   Date meetingDate,
                   Date meetingPlace) {
        this.id = id;
        this.companyId = companyId;
        this.header = header;
        this.body = body;
        this.creatingDate = creatingDate;
        this.meetingDate = meetingDate;
        this.meetingPlace = meetingPlace;
    }

    public int getId() {
        return id;
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

    public void setMeetingPlace(Date meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public Date getMeetingPlace() {
        return meetingPlace;
    }
}
