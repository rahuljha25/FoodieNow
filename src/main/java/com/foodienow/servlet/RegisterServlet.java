package com.foodienow.servlet;

import com.foodienow.daoimplementation.UserDaoImp;
import com.foodienow.model.User;
import com.foodienow.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String username = request.getParameter("username");
        String rawPassword = request.getParameter("password"); 
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        //  Hash the password before storing in DB
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);

        // Set into model
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword); 
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);

        // Save using DAO
        UserDaoImp dao = new UserDaoImp();
        boolean result = dao.addUser(user);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/Register.jsp?error=RegistrationFailed");
        }
    }
}
