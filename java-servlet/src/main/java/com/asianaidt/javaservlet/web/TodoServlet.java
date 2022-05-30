package com.asianaidt.javaservlet.web;

import com.asianaidt.javaservlet.domain.Todo;
import com.asianaidt.javaservlet.service.TodoService;
import com.asianaidt.javaservlet.service.TodoServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "todoServlet", value = "/todo")
public class TodoServlet extends HttpServlet {

    TodoService todoService = new TodoServiceImpl();

    private String message;

    public void init() {
        message = "Todo Service!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username;

        response.setContentType("text/html");
        if(request.getParameter("username") == null  || request.getParameter("username").trim().equals("")) {
            username = "user2";
        }
        else {
            username = request.getParameter("username");
        }

        System.out.println("username : " + username);
        List<Todo> result = todoService.getTodoByUsername(username);

        PrintWriter out = response.getWriter();
        if(!result.isEmpty()) {
            out.println("<html><body>");
            out.println("<h1>" + result.get(0).getUsername() + "</h1>");
            out.println("</body></html>");
        }else {
            out.println("<html><body>");
            out.println("<h1>해당 정보가 존재하지 않습니다.</h1>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}