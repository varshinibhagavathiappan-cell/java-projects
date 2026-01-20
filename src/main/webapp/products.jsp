<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>

<h2>Available Products</h2>

<form action="addToCart" method="post">
    <input type="checkbox" name="product" value="Laptop"> Laptop <br>
    <input type="checkbox" name="product" value="Mobile"> Mobile <br>
    <input type="checkbox" name="product" value="Headphones"> Headphones <br><br>

    <input type="submit" value="Add to Cart">
</form>

</body>
</html>