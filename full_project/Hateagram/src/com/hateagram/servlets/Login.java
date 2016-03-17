package com.hateagram.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hateagram.DAO.MySQLUtil;
import com.mysql.fabric.Response;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("./Index");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(username == null || password == null){
				throw new ServletException("Ivalid parameters");
			}

			if (MySQLUtil.isUserExisting(username, password)) {
				HttpSession httpSession = request.getSession(true);
				httpSession.setAttribute("username", username);
				httpSession.setMaxInactiveInterval(15 * 60);

				request.getRequestDispatcher("/WEB-INF/html/index.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/html/login.html").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to log in");
			response.sendRedirect("./Index");
		}
	}

}
