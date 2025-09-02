package com.foodienow.servlet;

import com.foodienow.daoimplementation.UserDaoImp;
import com.foodienow.model.User;
import com.foodienow.util.PasswordUtil;

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
		String rawPassword = request.getParameter("password");

		// Hash entered password before sending to DAO
		String hashedPassword = PasswordUtil.hashPassword(rawPassword);

		UserDaoImp dao = new UserDaoImp();
		User user = dao.getUserByEmailAndPassword(email, hashedPassword);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userObj", user);
			Boolean checkoutRequested = (Boolean) session.getAttribute("checkoutRequested");

			if (checkoutRequested != null && checkoutRequested) {
				session.removeAttribute("checkoutRequested");
				response.sendRedirect("checkout.jsp");
				return;
			}
			if (user.getRole().equalsIgnoreCase("customer")) {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			} else if (user.getRole().equalsIgnoreCase("admin")) {
				response.sendRedirect("admin.jsp");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp?error=InvalidCredentials");
			return;
		}
	}
}