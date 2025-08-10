package com.foodienow.servlet;

import com.foodienow.daoimplementation.UserDaoImp;
import com.foodienow.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setAddress(address);
        user.setRole("Customer");  // default role

        UserDaoImp dao = new UserDaoImp();
        boolean result = dao.addUser(user);

        if (result) {
            response.sendRedirect("Login.jsp"); // Redirect to login after successful registration
        } else {
            response.sendRedirect("Registration.jsp?error=RegistrationFailed");
        }
    }
}
