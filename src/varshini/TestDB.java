package varshini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class TestDB {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter department: ");
        String dept = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String pass = "NewStrongPassword";   // 👈 put your MySQL password

        try {
            // Load driver (you already tested this)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // Insert query
            String sql = "INSERT INTO students(name, roll, dept) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, roll);
            ps.setString(3, dept);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student registered successfully!");
            }

            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


