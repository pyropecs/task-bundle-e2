package com.customers.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customers.FetchCustomers;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{

        public void doGet(HttpServletRequest req,HttpServletResponse res){
                try{
                        int id = Integer.parseInt(req.getParameter("id"));
                        FetchCustomers fetchCustomers = new FetchCustomers();
                        fetchCustomers.deleteCustomers(id);
                        res.sendRedirect("/crud-task");
                }catch(Exception e){
                        System.out.println("something went wrong on deleteservlet on get request");
                        System.out.println(e.getMessage());
                }
                

        }



}




