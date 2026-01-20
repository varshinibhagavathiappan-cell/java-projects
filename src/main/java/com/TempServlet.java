package com;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/temp")
public class TempServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double c = Double.parseDouble(request.getParameter("celsius"));
        double f = (c * 9 / 5) + 32;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Fahrenheit: " + f + "</h2>");
    }
}