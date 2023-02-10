package com.todoapp.todoapp.model;

import java.util.Date;

public class Todo {
    private String title;
    private Date date;
    private User user;


    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, Date date) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
