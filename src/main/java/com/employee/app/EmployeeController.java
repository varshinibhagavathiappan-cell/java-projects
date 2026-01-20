package com.employee.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeController extends HttpServlet {

    Connection con;

    // init(): DB connection
    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/attendance_v2",
                "root",
                "NewStrongPassword"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // service(): register + login
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");

        try {
            if (action.equals("register")) {

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO employee(name, username, password) VALUES (?, ?, ?)");

                ps.setString(1, req.getParameter("name"));
                ps.setString(2, req.getParameter("username"));
                ps.setString(3, req.getParameter("password"));

                ps.executeUpdate();
                out.println("Employee Registered Successfully");

            } else if (action.equals("login")) {

                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM employee WHERE username=? AND password=?");

                ps.setString(1, req.getParameter("username"));
                ps.setString(2, req.getParameter("password"));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("Login Successful");
                } else {
                    out.println("Invalid Login Credentials");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // destroy(): close DB
    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}