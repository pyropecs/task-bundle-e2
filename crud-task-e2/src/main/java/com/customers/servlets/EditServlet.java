package com.customers.servlets;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customers.FetchCustomers;
import com.customers.beanclasses.Customer;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    private final int FIRST_CUSTOMER = 0;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            FetchCustomers fetchCustomers = new FetchCustomers();
            List<Customer> customerResult = fetchCustomers.getCustomers(id);
            req.setAttribute("customer", customerResult.get(FIRST_CUSTOMER));
            RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            System.out.println("something went wrong in EditServlet.doGet()");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }

    public void doPost(HttpServletRequest req,HttpServletResponse res){
      try {
        int id = Integer.parseInt(req.getParameter("id"));
        FetchCustomers fetchCustomers = new FetchCustomers();
        String customerName = req.getParameter("customer_name");
        long customerAge = Long.valueOf(req.getParameter("customer_age")); 
        double customerRating = Double.valueOf(req.getParameter("customer_rating"));
        fetchCustomers.editCustomers(id, customerName, customerAge, customerRating);
        res.setStatus(200);
        res.sendRedirect("/crud-task"); 
      } catch (Exception e) {
        res.setStatus(500);
        System.out.println("error in EditServlet.doPost()");
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    
    }

}
