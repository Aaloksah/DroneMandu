<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h1 {
            color: #7d2ae8;
        }
        p {
            color: #333;
        }
        .back-btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #7d2ae8;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            transition: background-color 0.3s;
        }
        .back-btn:hover {
            background-color: #7d2ae8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Successfully Placed!</h1>
        <p>Your order has been successfully processed. Thank you for shopping with us!</p>
        <p>We'll send you an email confirmation shortly.</p>
        <a href="home.jsp" class="back-btn">Back to Home</a>
    </div>
</body>
