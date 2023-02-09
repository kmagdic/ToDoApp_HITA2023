package com.todoapp.todoapp;

import com.todoapp.todoapp.model.Todo;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.model.UserRepositoryMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {
    User currUser;

    List<Todo> todoList = new ArrayList<>();

    @Autowired
    UserRepositoryMem userRepository;

    public TodoController() {
        todoList.add(new Todo("Naučiti HTML", new Date()));
        todoList.add(new Todo("Naučiti CSS", new Date()));
        todoList.add(new Todo("Naučiti JS", new Date()));
    }


    @GetMapping("/todos")
    public String showTodos(Model model) {
        model.addAttribute(todoList);
        model.addAttribute("currUser", currUser);
        return "employee_todo_list_user.html";
    }


    @GetMapping("/addNewTodo")
    public String addNewTodo(String title) {
        todoList.add(new Todo(title, new Date()));

        return "redirect:/todos";

    }

    @GetMapping("/delete")
    public String delete(String title) {
        for(Todo todo : todoList) {
            if(todo.getTitle().equals(title)) {
                todoList.remove(todo);
                break;
            }
        }

        return "redirect:/todos";

    }


    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute(userRepository.getUserListWhichAreEmployees());

        return "employees.html";
    }


    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/loginProcess")
    public String login(String email, String pass, Model model) {

        // find user in list
        User user = userRepository.getUserByUsernameAndPassword(email, pass);

        if(user != null) {
            System.out.println("User found: " + user);
            currUser = user;
            if(user.getType() == 0)
                return "redirect:/todos";
            else
                return "redirect:/users";
        } else {
            model.addAttribute("userMessage","User not found!");
            return "login.html";
        }


    }


}
