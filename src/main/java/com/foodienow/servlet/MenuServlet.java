  //Handles Menu listing and menu-related requests
package com.foodienow.servlet;

import com.foodienow.dao.MenuDao;
import com.foodienow.dao.RestaurantDao;
import com.foodienow.daoimplementation.MenuDaoImp;
import com.foodienow.daoimplementation.RestaurantDaoImp;
import com.foodienow.model.Menu;
import com.foodienow.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String restIdParam = request.getParameter("restaurantId");

            if (restIdParam != null && !restIdParam.isEmpty()) {
                int restaurantId = Integer.parseInt(restIdParam);

                // Fetch menus
                MenuDao menuDao = new MenuDaoImp();
                List<Menu> menuList = menuDao.getAllMenusByResturant(restaurantId);

                // Fetch restaurant name
                RestaurantDao restaurantDao = new RestaurantDaoImp();
                Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);
                String restaurantName = (restaurant != null) ? restaurant.getName() : "Menu";

                // Pass data to JSP
                request.setAttribute("menuList", menuList);
                request.setAttribute("restaurantName", restaurantName);
                
                request.getRequestDispatcher("menu.jsp").forward(request, response);
            } else {
                response.sendRedirect("home.jsp");  // fallback if restaurantId is missing
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Optional error page
        }
    }
}
