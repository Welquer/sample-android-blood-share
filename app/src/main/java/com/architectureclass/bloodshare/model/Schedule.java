package com.architectureclass.bloodshare.model;

import java.util.Date;

public class Schedule {

    private String id;
    private Date createdAt;
    private String place;
    private String recipient;
    private Date dueDate;
    private boolean done;

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPlace() {
        return place;
    }

    public String getRecipient() {
        return recipient;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
