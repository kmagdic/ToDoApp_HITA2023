package com.todoapp.todoapp;

import com.todoapp.todoapp.model.UserRepositoryInMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TodoSupervisorController {

    @Autowired
    UserRepositoryInMem userRepositoryInMem;

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("userList", userRepositoryInMem.getUsers());
        return "employees.html";
    }
}
