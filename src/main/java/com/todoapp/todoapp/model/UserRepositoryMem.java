package com.todoapp.todoapp.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryMem {
    List<User> userList = new ArrayList<>();

    public UserRepositoryMem() {
        // one admin/supervisor user
        User u = new User("karlo@gmail.com", "test123", "Admin", "Admin", "123");
        u.setType(1);
        userList.add(u);

        // three employees
        userList.add(new User("dario@gmail.com", "test123", "Dario", "Mažukić", "1232132313"));
        userList.add(new User("slaven@gmail.com", "test123", "Slaven", "Bilić", "1232132313"));
        userList.add(new User("robert@gmail.com", "test123", "Robert", "Prosinečki", "1232132313"));

    }

    public List<User> getUserList() {
        return userList;
    }


    public List<User> getUserListWhichAreEmployees() {
        List<User> userListEmp = new ArrayList<>();

        for (User u : userList) {
            if(u.getType() == 0) // 0 is employee
                userListEmp.add(u);
        }

        return userListEmp;
    }

    public User getUserByUsernameAndPassword(String email, String password) {
        User user = null;
        for (User u : userList) {
            if(u.getEmail().equals(email) &&
                    u.getPassword().equals(password))
                user = u;

        }
        return user;
    }

    public User getUserById(int id) {
        User user = null;
        for (User u : userList) {
            if(u.getId() == id)
                user = u;

        }
        return user;
    }
}
