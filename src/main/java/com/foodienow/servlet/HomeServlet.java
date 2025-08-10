//Handles Restaurant listing and homepage requests
package com.foodienow.servlet;

import com.foodienow.dao.RestaurantDao;
import com.foodienow.daoimplementation.RestaurantDaoImp;
import com.foodienow.model.Restaurant;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestaurantDao dao = new RestaurantDaoImp();  // ✅ DAO initialized properly
        
        List<Restaurant> list = dao.getAllRestaurants();  // ✅ Use the correct variable name
//        System.out.println(list);
        request.setAttribute("restaurants", list);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
}
