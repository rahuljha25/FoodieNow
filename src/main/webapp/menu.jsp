<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.foodienow.model.Menu" %>

<html>
<head>
    <title>FoodieNow - Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin: 25px 0;
            font-size: 24px;
            color: #333;
        }

        .menu-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
            gap: 20px;
            padding: 20px;
            max-width: 1200px;
            margin: auto;
        }

        .menu-card {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
            overflow: hidden;
            transition: transform 0.2s ease;
        }

        .menu-card:hover {
            transform: scale(1.02);
        }

        .menu-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-bottom: 1px solid #eee;
        }

        .menu-card-content {
            padding: 15px;
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }

        .menu-card h3 {
            margin: 5px 0;
            font-size: 18px;
            color: #222;
        }

        .menu-card p {
            margin: 5px 0;
            color: #555;
            font-size: 14px;
        }

        .price {
            font-weight: bold;
            color: #28a745;
            margin: 10px 0;
            font-size: 16px;
        }

        .add-to-cart-btn {
            background: #ff5722;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s;
            margin-top: auto;
        }

        .add-to-cart-btn:hover {
            background: #e64a19;
        }

        .back-link {
            display: block;
            text-align: center;
            margin: 15px auto;
            text-decoration: none;
            color: #007bff;
            font-size: 14px;
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
                <div class="menu-card-content">
                    <h3><%= m.getName() %></h3>
                    <p><%= m.getDescription() %></p>
                    <p class="price">₹ <%= m.getPrice() %></p>

                    <form action="cart" method="post">
                        <input type="hidden" name="restaurantId" value="<%= m.getRestaurantId() %>">
                        <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
                        <input type="hidden" name="quantity" value="1">
                        <input type="hidden" name="action" value="add">
                        <button type="submit" class="add-to-cart-btn">Add to Cart</button>
                    </form>
                </div>
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
    <%@ include file="footer.jsp" %>
</body>
</html>
