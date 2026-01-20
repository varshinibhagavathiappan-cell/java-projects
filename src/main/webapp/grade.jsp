<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP Student Grade Calculator</title>
</head>
<body>

<h2>JSP Student Grade Calculator</h2>

<form method="get">
    Enter Marks:
    <input type="text" name="marks"><br><br>

    <input type="submit" value="Check Grade">
</form>

<%
    String m = request.getParameter("marks");

    if (m != null) {
        int marks = Integer.parseInt(m);
        String grade;

        if (marks >= 80) {
            grade = "A";
        } else if (marks >= 60) {
            grade = "B";
        } else {
            grade = "C";
        }
%>

<h3>Grade = <%= grade %></h3>

<%
    }
%>

</body>
</html>