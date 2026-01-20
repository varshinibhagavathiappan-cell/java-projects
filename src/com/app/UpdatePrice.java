package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdatePrice {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product id: ");
        int id = sc.nextInt();

        System.out.print("Enter new price: ");
        double price = sc.nextDouble();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "NewStrongPassword");

            String sql = "UPDATE products SET price=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Price updated successfully!");
            } else {
                System.out.println("Product not found!");
            }

            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}