package com.todoapp.todoapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {

    List<Todo> todoList = new ArrayList<>();

    public TodoController() {
        todoList.add(new Todo("Naučiti HTML", new Date()));
        todoList.add(new Todo("Naučiti CSS", new Date()));
        todoList.add(new Todo("Naučiti JS", new Date()));

    }


    @GetMapping("/todos")
    public String showTodos(Model model) {
        model.addAttribute(todoList);
        System.out.println("Prikaz stranice svih todo-ova 123");
        return "employee_todo_list_user.html";
    }


    @GetMapping("/addNewTodo")
    public String addNewTodo(String title) {
        todoList.add(new Todo(title));

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


}
