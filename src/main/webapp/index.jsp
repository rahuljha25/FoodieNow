<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome - FoodieNow</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #ff9a9e, #fad0c4);
            margin: 0;
            padding: 0;
            text-align: center;
        }
        h1 {
            font-size: 60px;
            margin-top: 100px;
            color: #ffffff;
            text-shadow: 2px 2px 4px #333;
            animation: fadeIn 2s ease-in-out;
        }
        p {
            font-size: 20px;
            color: #fff;
            max-width: 600px;
            margin: auto;
            margin-top: 20px;
        }
        .btn {
            display: inline-block;
            margin-top: 40px;
            margin-right: 10px;
            padding: 15px 30px;
            font-size: 20px;
            color: white;
            background-color: #ff5e62;
            border-radius: 30px;
            text-decoration: none;
            transition: background 0.3s ease;
        }
        .btn:hover {
            background-color: #ff2e36;
        }
        .btn.secondary {
            background-color: #4CAF50;
        }
        .btn.secondary:hover {
            background-color: #3e8e41;
        }
        .btn.guest {
            background-color: #2196F3;
        }
        .btn.guest:hover {
            background-color: #0b7dda;
        }
        @keyframes fadeIn {
            0% {opacity: 0;}
            100% {opacity: 1;}
        }
    </style>
</head>
<body>
    <h1>🍽 Welcome to FoodieNow 🍽</h1>
    <p>Your one-stop destination for ordering delicious food online.  
       Fresh meals, quick delivery, and endless flavors at your fingertips!</p>

    <a class="btn" href="Login.jsp">Login</a>
    <a class="btn secondary" href="Register.jsp">Sign Up</a>
    <a class="btn guest" href="home?guest=true">Continue as Guest</a>
</body>
</html>

