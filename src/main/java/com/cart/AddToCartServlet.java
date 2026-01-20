package com.cart;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        String[] products = request.getParameterValues("product");

        ArrayList<String> cart =
                (ArrayList<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        if (products != null) {
            for (String p : products) {
                cart.add(p);
            }
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("cart.jsp");
    }
}