package com.todoapp.todoapp.model;

public class User {
    static int idCounter = 0;

    int id;
    String email;
    String password;
    int type = 0; // 0 - employee, 1 - supervisor

    String firstName;
    String lastName;
    String oib;




    public User(String email, String password) {
        this.email = email;
        this.password = password;

        id = idCounter++;
    }

    public User(String email, String password, String firstName, String lastName, String oib) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.oib = oib;

        id = idCounter++;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public int getId() {
        return id;
    }
}
