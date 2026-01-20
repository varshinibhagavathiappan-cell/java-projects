package varshini;   // use your package name

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BankBalanceChecker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String pass = "NewStrongPassword";   // change this

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // SELECT query
            String sql = "SELECT holder_name, balance FROM accounts WHERE acc_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, accNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("holder_name");
                double balance = rs.getDouble("balance");

                System.out.println("Account Holder: " + name);
                System.out.println("Balance: ₹" + balance);
            } else {
                System.out.println("Account not found!");
            }

            rs.close();
            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
