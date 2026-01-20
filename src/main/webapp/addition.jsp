<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP Addition Program</title>
</head>
<body>

<h2>JSP Addition Program</h2>

<form method="get">
    Number 1:
    <input type="text" name="num1"><br><br>

    Number 2:
    <input type="text" name="num2"><br><br>

    <input type="submit" value="Add">
</form>

<%
    String n1 = request.getParameter("num1");
    String n2 = request.getParameter("num2");

    if (n1 != null && n2 != null) {
        int a = Integer.parseInt(n1);
        int b = Integer.parseInt(n2);
        int sum = a + b;
%>

<h3>Sum = <%= sum %></h3>

<%
    }
%>

</body>
</html>