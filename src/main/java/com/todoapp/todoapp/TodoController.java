package com.todoapp.todoapp;

import com.todoapp.todoapp.model.Todo;
import com.todoapp.todoapp.model.User;
import com.todoapp.todoapp.model.UserRepositoryMem;
import jakarta.annotation.PostConstruct;
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

    List<Todo> getTodoListForUserId(int userId) {
        // filter todos only for that user
        List<Todo> todoForUser = new ArrayList<>();
        for(Todo todo: todoList) {
            if(todo.getUser() != null)
                if(todo.getUser().getId() == userId)
                    todoForUser.add(todo);
        }
        return todoForUser;
    }

    @Autowired
    UserRepositoryMem userRepository;

    @PostConstruct
    public void initData() {

        Todo todo1 = new Todo("Naučiti HTML", new Date());
        todo1.setUser(userRepository.getUserById(1));
        todoList.add(todo1);

        Todo todo2 = new Todo("Naučiti CSS", new Date());
        todo2.setUser(userRepository.getUserById(2));
        todoList.add(todo2);

        Todo todo3 = new Todo("Naučiti JS", new Date());
        todo3.setUser(userRepository.getUserById(2));
        todoList.add(todo3);
    }


    // EMPLOYEE METHODS

    @GetMapping("/todos")
    public String showTodos(int userId, Model model) {
        model.addAttribute(getTodoListForUserId(userId));
        model.addAttribute("currUser", userRepository.getUserById(userId));
        return "employee_todo_list_user.html";
    }


    @GetMapping("/addNewTodo")
    public String addNewTodo(int userId, String title) {
        Todo newTodo = new Todo(title, new Date());
        newTodo.setUser(userRepository.getUserById(userId));
        todoList.add(newTodo);

        return "redirect:/todos?userId=" + userId;

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



    // SUPERVISOR METHODS

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute(userRepository.getUserListWhichAreEmployees());

        return "supervisor_employees.html";
    }

    @GetMapping("/showToDosForUser")
    public String showToDosForUser(int userId, Model model) {
        User user = userRepository.getUserById(userId);
        model.addAttribute(user);

        // filter todos only for that user
        model.addAttribute(getTodoListForUserId(userId));

        return "supervisor_employee_todos.html";
    }








    // LOGIN METHODS

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
                return "redirect:/todos?userId=" + user.getId();
            else
                return "redirect:/users";
        } else {
            model.addAttribute("userMessage","User not found!");
            return "login.html";
        }


    }


}
