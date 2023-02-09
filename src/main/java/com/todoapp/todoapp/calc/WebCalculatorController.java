package com.todoapp.todoapp.calc;

import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebCalculatorController {

    List<String> operationHistory = new ArrayList<>();


    @GetMapping("/sum2")
    public String calculateSum2(int a, int b, char oper, Model model) throws IOException {
        int result = 0;

        if(oper == '+' || oper == ' ')
            result = a + b;
        else if(oper == '*')
            result = a * b;
        else if(oper == '-')
            result = a - b;
        else if(oper == '/')
            result = a / b;

        model.addAttribute("result", result);
        return "calc_result.html";

    }


    @GetMapping("/sum")
    public void calculateSum(int a, int b, char oper, ServletResponse response) throws IOException {
        int result = 0;

        if(oper == '+' || oper == ' ')
            result = a + b;
        else if(oper == '*')
            result = a * b;
        else if(oper == '-')
            result = a - b;
        else if(oper == '/')
            result = a / b;

        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Calculator</h1>");
        response.getWriter().println("<br/>Result is: " + result);

        response.getWriter().println("<h3>History</h3>");
        for(String hisResult : operationHistory)
            response.getWriter().println("<br/>" + hisResult);

        operationHistory.add("Calculation: " + a + oper + b + "=" + result);

        response.getWriter().println("<br/><a href='calc.html'>Back to calculator</a>");
        response.getWriter().println("</body>");

    }

    @GetMapping("/test")
    public void test(ServletResponse response) throws IOException {
        response.getWriter().println("Test 123");
    }
}
