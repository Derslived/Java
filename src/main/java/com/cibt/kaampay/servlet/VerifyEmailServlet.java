/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.servlet;

import com.cibt.kaampay.service.UserService;
import com.cibt.kaampay.service.impl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Derslived
 */
@WebServlet( name="verfiyEmail", urlPatterns = "/verifyemail/*")
public class VerifyEmailServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            String page="";
           if( userService.verify(request.getParameter("email"))){
               page="/login";
           }else{
               page="/error";
           }
           response.sendRedirect(request.getContextPath() + page);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
