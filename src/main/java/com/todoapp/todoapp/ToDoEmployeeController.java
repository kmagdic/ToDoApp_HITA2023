package com.todoapp.todoapp;

import com.todoapp.todoapp.model.TodoItem;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.model.UserRepositoryInMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ToDoEmployeeController {

    List<TodoItem> todoItemList = new ArrayList<>();

    @Autowired
    UserRepositoryInMem userRepositoryInMem;

    ToDoEmployeeController() {
        todoItemList.add(new TodoItem("Oprati veš", new Date()));
        todoItemList.add(new TodoItem("Pospremiti kuhinju", new Date()));
        todoItemList.add(new TodoItem("Treći item", new Date()));
        todoItemList.add(new TodoItem("Četvrti item", new Date()));
    }


    @GetMapping("/")
    public String showToDo(Model model) {
        model.addAttribute(todoItemList);

        return "employee_todo_list_user.html";
    }

    @GetMapping("/addNewItem")
    public String addNewItem(String todo, String user) {
        todoItemList.add(new TodoItem(todo, user, new Date()));
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/loginProcess")
    public String login(String email, String pass, Model model) {
        // find user in list
        User user = null;
        for (User u : userRepositoryInMem.getUsers()) {
            if(u.getEmail().equals(email) &&
                    u.getPassword().equals(pass))
                user = u;

        }

        if(user != null) {
            System.out.println("User found: " + user);
            return "redirect:/";
        } else {
            model.addAttribute("userMessage",
                    "User not found!");
            return "login.html";
        }


    }


}
