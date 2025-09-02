// Handles Restaurant listing and homepage requests
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//added by me
    	String guestParam = request.getParameter("guest");
    	if ("true".equals(guestParam)) {
    	    HttpSession session = request.getSession(false);
    	    if (session != null) {
    	        session.removeAttribute("userObj"); // remove logged-in user info
    	    }
    	}
        
        RestaurantDao dao = new RestaurantDaoImp();

        // Get search parameter
        String searchQuery = request.getParameter("search");
        List<Restaurant> list;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // If search text is entered, search restaurants
            list = dao.searchRestaurants(searchQuery.trim());
            if (list.isEmpty()) {
                request.setAttribute("message", "No restaurant available for your search.");
            }
        } else {
            // If no search, load all restaurants
            list = dao.getAllRestaurants();
        }

        request.setAttribute("restaurants", list);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
}
