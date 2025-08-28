<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - FoodieNow</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .container {
            width: 500px;
            margin: 40px auto;
            padding: 25px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
        }
        input[type=text], input[type=password], input[type=email], select {
            width: 100%;
            padding: 12px;
            margin: 8px 0 16px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        input[type=submit] {
            width: 100%;
            background-color: #28a745;
            color: white;
            padding: 14px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background-color: #218838;
        }
        .link {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>User Registration</h2>
    <form action="register" method="post">
        

        <label for="username">User Name</label>
        <input type="text" id="username" name="username" placeholder="Choose a username" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Enter password" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="example@email.com" required>

        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" placeholder="10-digit mobile number" required>

        <label for="address">Address</label>
        <input type="text" id="address" name="address" placeholder="City / Address" required>

        <label for="role">Role</label>
        <select name="role" id="role" required>
            <option value="">--Select Role--</option>
            <option value="Customer">Customer</option>
            <option value="Admin">Admin</option>
            <option value="Admin">Restaurant owner</option>
            
        </select>

        <input type="submit" value="Register">
    </form>

    <div class="link">
        Already have an account? 
        <a href="<%= request.getContextPath() %>/Login.jsp">Login here</a>
    </div>
</div>
</body>
</html>
