package com.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LibraryServlet extends HttpServlet {

    Connection con;

    // init() - runs once when server starts
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/librarydb",
                "root",
                "NewStrongPassword"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // service() - runs for every request
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");

        try {
            // VIEW BOOKS
            if ("view".equals(action)) {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM books");

                out.println("<h2>Book List</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt(1) + "</td>");
                    out.println("<td>" + rs.getString(2) + "</td>");
                    out.println("<td>" + rs.getString(3) + "</td>");
                    out.println("<td>" + rs.getDouble(4) + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }
            // ADD BOOK
            else {
                String title = req.getParameter("title");
                String author = req.getParameter("author");
                double price = Double.parseDouble(req.getParameter("price"));

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO books(title, author, price) VALUES (?, ?, ?)"
                );

                ps.setString(1, title);
                ps.setString(2, author);
                ps.setDouble(3, price);
                ps.executeUpdate();

                out.println("<h3>Book Added Successfully</h3>");
                out.println("<a href='addBook.html'>Add Another Book</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // destroy() - runs when server stops
    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}