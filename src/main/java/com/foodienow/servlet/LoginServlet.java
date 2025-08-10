package com.foodienow.servlet;

import com.foodienow.daoimplementation.UserDaoImp;
import com.foodienow.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDaoImp dao = new UserDaoImp();
        User user = dao.getUserByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userObj", user);

            response.sendRedirect("home"); // ✅ Redirect to restaurant/home after login
        } else {
            response.sendRedirect("Login.jsp?error=InvalidCredentials");
        }
    }
}
