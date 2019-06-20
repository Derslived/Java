/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.servlet;

import com.cibt.kaampay.entity.User;
import com.cibt.kaampay.service.UserService;
import com.cibt.kaampay.service.impl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Derslived
 */
@WebServlet(urlPatterns = "/login/*")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String page = "";
        try {
            User user = userService.login(request.getParameter("email"),
                    request.getParameter("password"));
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                page = "/admin";
            } else {
                page = "/login?error";
            }
            response.sendRedirect(request.getContextPath() + page);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
