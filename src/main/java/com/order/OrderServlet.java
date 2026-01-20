package com.order;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/orderdb",
                "root",
                "NewStrongPassword"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String item = request.getParameter("item");
        int qty = Integer.parseInt(request.getParameter("qty"));

        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders(customer_name, item, quantity) VALUES (?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, item);
            ps.setInt(3, qty);
            ps.executeUpdate();

            out.println("<h2>Order Placed Successfully</h2>");
            out.println("Name: " + name + "<br>");
            out.println("Item: " + item + "<br>");
            out.println("Quantity: " + qty);

        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }

    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}