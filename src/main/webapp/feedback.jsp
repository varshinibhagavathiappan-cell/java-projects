<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP Feedback Form</title>
</head>
<body>

<h2>Feedback Form</h2>

<form method="post">
    Name:
    <input type="text" name="name"><br><br>

    Feedback:<br>
    <textarea name="feedback" rows="4" cols="30"></textarea><br><br>

    <input type="submit" value="Submit Feedback">
</form>

<%
    String name = request.getParameter("name");
    String feedback = request.getParameter("feedback");

    if (name != null && feedback != null) {
%>

<h3>Thank you, <%= name %>, for your feedback!</h3>

<%
    }
%>

</body>
</html>