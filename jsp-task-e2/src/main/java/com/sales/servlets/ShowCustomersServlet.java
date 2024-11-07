package com.sales.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customers")
public class ShowCustomersServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        
        
        
        try {

                String field = req.getParameter("order");
                String order = req.getParameter("sort");
                req.setAttribute("field", field);
                req.setAttribute("order", order);
                RequestDispatcher rd = req.getRequestDispatcher("customers.jsp");
                rd.forward(req, res);
         

        } catch (
            IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong CustomerServvlet");
            System.out.println(e.getMessage());
            
        }

    }

}
