package com.todoapp.todoapp.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id")
    private Category category;


    public Todo() {
    }

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

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
