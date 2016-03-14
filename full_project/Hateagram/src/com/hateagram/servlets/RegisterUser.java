package com.hateagram.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hateagram.DAO.MySQLUtil;
import com.hateagram.model.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			MySQLUtil.registerUser((IUser) (new User(name, Integer.parseInt(age), username, password, email)));
			request.getRequestDispatcher("/Index").forward(request, response);
			
		} catch (Exception e) {
			throw new ServletException("Unable to register user!", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
