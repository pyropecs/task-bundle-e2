
package com.sales.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class ShowMainServlet extends HttpServlet {

        public void doGet(HttpServletRequest req, HttpServletResponse res) {

                try {

                        RequestDispatcher rd = req.getRequestDispatcher("main.jsp");
                        rd.forward(req, res);

                } catch (IOException e) {
                        System.out.println(e.getMessage());
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }

        }

}
