<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.foodienow.model.Restaurant" %>

<html>
<head>
    <title>FoodieNow - Restaurants</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
        }

        .restaurant-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .restaurant-card {
            width: 300px;
            background: white;
            border-radius: 12px;
            margin: 15px;
            padding: 15px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            transition: 0.3s;
        }

        .restaurant-card:hover {
            transform: scale(1.02);
        }

        .restaurant-card img {
            width: 100%;
            height: 180px;
            border-radius: 8px;
            object-fit: cover;
        }

        .restaurant-card h3 {
            margin: 10px 0 5px;
        }

        .restaurant-card p {
            margin: 5px 0;
            color: #555;
        }

        .rating {
            font-weight: bold;
            color: #ff9800;
        }

        .status {
            font-weight: bold;
            margin-top: 5px;
        }

        .status.active {
            color: green;
        }

        .status.inactive {
            color: red;
        }

        .menu-link {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 14px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        .menu-link:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Explore Restaurants</h2>

    <div class="restaurant-container">
        <%
            List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant r : restaurants) {
        %>
            <div class="restaurant-card">
                <img src="<%=r.getImageUrl() %>" alt="Image">
                <h3><%= r.getName() %></h3>
                <p><strong>Cuisine:</strong> <%= r.getCuisine() %></p>
                <p><strong>Address:</strong> <%= r.getAddress() %></p>
                <p class="rating">⭐ <%= r.getRating() %></p>
                <p><strong>Delivery Time:</strong> <%= r.getDeliveryTime() %> mins</p>
                <p class="status <%= r.isActive() ? "active" : "inactive" %>">
                    <%= r.isActive() ? "Open Now" : "Closed" %>
                </p>

                <!-- ✅ Add View Menu link -->
                <a class="menu-link" href="menu?restaurantId=<%= r.getRestaurantId() %>">View Menu</a>
            </div>
        <%
                }
            } else {
        %>
            <p style="text-align:center;">No restaurants available at the moment.</p>
        <%
            }
        %>
    </div>
</body>
</html>
