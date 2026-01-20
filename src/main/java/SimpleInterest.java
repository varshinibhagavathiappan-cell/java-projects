import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SimpleInterest extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        double p = Double.parseDouble(request.getParameter("p"));
        double r = Double.parseDouble(request.getParameter("r"));
        double t = Double.parseDouble(request.getParameter("t"));

        double si = (p * r * t) / 100;

        out.println("Simple Interest = " + si);
    }
}