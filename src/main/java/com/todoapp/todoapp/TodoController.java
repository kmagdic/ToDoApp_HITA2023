package com.todoapp.todoapp;

import com.todoapp.todoapp.model.*;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/init")
    String init() {

        List<User> testUsers = (List<User>) userRepository.findAll();
        System.out.println(testUsers);


        // List<User> testUsers2 = (List<User>) userRepository.findByTitleAndSortNative("Karlo");
        // System.out.println(testUsers2);


        User user = new User("admin@mail.com", "test123");
        user.setType(1);
        userRepository.save(user);


        return "login.html";
    }

    // EMPLOYEE METHODS

    @GetMapping("/todos")
    public String showTodos(long userId, Model model) {

        // add current user
        User currUser = userRepository.findById(userId).get();
        model.addAttribute("currUser", currUser);

        // add all todo for that user
        model.addAttribute(todoRepository.findByUser(currUser));

        // add all categories for dropdown
        model.addAttribute(categoryRepository.findAll());


        return "employee_todo_list_user.html";
    }


    @GetMapping("/addNewTodo")
    public String addNewTodo(long userId, String title, long categoryId) {
        User currUser = userRepository.findById(userId).get();
        Category currCategory = categoryRepository.findById(categoryId).get();


        Todo newTodo = new Todo(title, new Date());
        newTodo.setUser(currUser);
        newTodo.setCategory(currCategory);

        todoRepository.save(newTodo);

        return "redirect:/todos?userId=" + userId;

    }


    @GetMapping("/delete")
    public String delete(Long id) {

        Todo todo = todoRepository.findById(id).get();
        todoRepository.delete(todo);


        return "redirect:/todos?userId=" + todo.getUser().getId();

    }




    // SUPERVISOR METHODS

    @GetMapping("/users")
    public String showUsers(Model model) {
        // add current user
        model.addAttribute(userRepository.findByType(0));

        return "supervisor_employees.html";
    }

    @GetMapping("/showToDosForUser")
    public String showToDosForUser(long userId, Model model) {
        User user = userRepository.findById(userId).get();
        model.addAttribute(user);

        // filter todos only for that user
        model.addAttribute(todoRepository.findByUser(user));

        return "supervisor_employee_todos.html";
    }


    @GetMapping("/deleteBySupervisor")
    public String deleteBySupervisor(Long id) {
        Todo todo = todoRepository.findById(id).get();
        todoRepository.delete(todo);

        return "redirect:/showToDosForUser?userId=" + todo.getUser().getId();

    }

    @GetMapping("/addNewEmployee")
    public String addNewEmpoyee(String fname, String lname, String email, String pass) {
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(pass);

        userRepository.save(user);

        return "redirect:/users";
    }


    // LOGIN METHODS

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/loginProcess")
    public String login(String email, String pass, Model model) {

        // find user in list
        User user = userRepository.findByEmailAndPassword(email, pass);

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


    // UTITILTY METHODS

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
