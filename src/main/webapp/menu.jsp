<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.foodienow.model.Menu" %>

<html>
<head>
    <title>FoodieNow - Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fafafa;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin: 20px 0;
        }

        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .menu-card {
            width: 280px;
            background: white;
            border-radius: 12px;
            margin: 15px;
            padding: 15px;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
            transition: 0.3s;
        }

        .menu-card:hover {
            transform: scale(1.02);
        }

        .menu-card img {
            width: 100%;
            height: 180px;
            border-radius: 8px;
            object-fit: cover;
        }

        .menu-card h3 {
            margin: 10px 0 5px;
        }

        .menu-card p {
            margin: 5px 0;
            color: #555;
        }

        .price {
            font-weight: bold;
            color: #388e3c;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-bottom: 20px;
            text-decoration: none;
            color: #2196F3;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <a class="back-link" href="home">&larr; Back to Restaurants</a>
    <h2>Menu Items</h2>

    <div class="menu-container">
        <%
            List<Menu> menus = (List<Menu>) request.getAttribute("menuList");
            if (menus != null && !menus.isEmpty()) {
                for (Menu m : menus) {
        %>
            <div class="menu-card">
                <img src="<%= m.getImageUrl() %>" alt="Menu Image">
                <h3><%= m.getName() %></h3>
                <p><%= m.getDescription() %></p>
                <p class="price">₹ <%= m.getPrice() %></p>
            </div>
        <%
                }
            } else {
        %>
            <p style="text-align:center;">No menu items found for this restaurant.</p>
        <%
            }
        %>
    </div>
</body>
</html>
