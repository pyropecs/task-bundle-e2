package com.customers.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customers.FetchCustomers;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

        public void doGet(HttpServletRequest req, HttpServletResponse res) {

                try {
                        RequestDispatcher dispatcherServlet = req.getRequestDispatcher("create.jsp");
                        dispatcherServlet.forward(req, res);

                } catch (Exception e) {
                        System.out.println("something went wrong get request in addServlet");
                        System.out.println(e.getMessage());
                }

        }

        public void doPost(HttpServletRequest req, HttpServletResponse res) {
                try {
                        FetchCustomers fetchInstance = new FetchCustomers();
                        String name = req.getParameter("customer_name");
                        long age = Long.valueOf(req.getParameter("customer_age"));
                        double rating = Double.valueOf(req.getParameter("customer_rating"));
                        fetchInstance.insertCustomers(name, age, rating);
                        res.setStatus(200);
                        res.sendRedirect("/crud-task");
                } catch (Exception e) {
                        System.out.println("something went wrong in post request addservlet");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                }

        }

}
