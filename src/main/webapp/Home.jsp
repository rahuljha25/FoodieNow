<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.foodienow.model.Restaurant" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>FoodieNow - Restaurants</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #fafafa;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin: 30px 0 20px;
            font-size: 28px;
            color: #333;
        }

        .restaurant-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 25px;
            padding: 20px 50px;
        }

        .restaurant-card {
            background: #fff;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            transition: transform 0.25s ease, box-shadow 0.25s ease;
            display: flex;
            flex-direction: column;
        }

        .restaurant-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0,0,0,0.12);
        }

        .restaurant-card img {
    width: 100%;
    height: 200px;        /* same fixed height for all images */
    object-fit: cover;    /* crop and fill uniformly */
    object-position: center; /* center focus */
    border-bottom: 1px solid #eee;
    display: block;
}

        .restaurant-info {
            padding: 15px;
            flex-grow: 1;
        }

        .restaurant-info h3 {
            margin: 5px 0;
            font-size: 20px;
            color: #222;
        }

        .restaurant-info p {
            margin: 4px 0;
            color: #555;
            font-size: 14px;
        }

        .rating {
            font-weight: bold;
            color: #f39c12;
        }

        .status {
            font-weight: bold;
            font-size: 14px;
            margin-top: 6px;
        }

        .status.active {
            color: #27ae60;
        }

        .status.inactive {
            color: #e74c3c;
        }

        .menu-link {
            display: block;
            text-align: center;
            margin: 12px 15px 15px;
            padding: 10px;
            background: linear-gradient(135deg, #ff4b2b, #ff416c);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background 0.3s;
        }

        .menu-link:hover {
            background: linear-gradient(135deg, #e84393, #d63031);
        }
    </style>
</head>
<body>
    <h2>🍴 Explore Restaurants</h2>

    <div class="restaurant-container">
    <%
        List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
        if (restaurants != null && !restaurants.isEmpty()) {
            for (Restaurant r : restaurants) {
    %>
        <div class="restaurant-card">
            <img src="<%=r.getImageUrl() %>" alt="Image">
            <div class="restaurant-info">
                <h3><%= r.getName() %></h3>
                <p><strong>Cuisine:</strong> <%= r.getCuisine() %></p>
                <p><strong>Address:</strong> <%= r.getAddress() %></p>
                <p class="rating">⭐ <%= r.getRating() %></p>
                <p><strong>Delivery:</strong> <%= r.getDeliveryTime() %> mins</p>
                <p class="status <%= r.isActive() ? "active" : "inactive" %>">
                    <%= r.isActive() ? "Open Now" : "Closed" %>
                </p>
            </div>
            <a class="menu-link" href="menu?restaurantId=<%= r.getRestaurantId() %>">View Menu</a>
        </div>
    <%
            }
        } else {
    %>
        <p style="text-align:center; font-size:16px; color:#777;">No restaurants available at the moment.</p>
    <%
        }
    %>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
