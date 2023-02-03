package com.todoapp.todoapp.model;

import java.util.Date;

public class TodoItem {
    String title;
    String user;
    Date date;

    public TodoItem(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public TodoItem(String title, String user, Date date) {
        this.title = title;
        this.user = user;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
