package com.todoapp.todoapp.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryInMem {
    List<User> users = new ArrayList<>();

    public UserRepositoryInMem() {
        users.add(new User("drsacer@gmail.com", "Sačer"));
        users.add(new User("karlo@agileway.com", "test123"));
        users.add(new User("mona@agileway.com", "test123"));
    }

    public List<User> getUsers() {
        return users;
    }
}
