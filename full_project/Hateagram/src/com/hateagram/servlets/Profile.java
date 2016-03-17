package com.hateagram.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		CommonFunctions.isLoggedIn(request);
		if(request.getParameter("user")==null){
			throw new ServletException("Ivalid parameters");
		}
		request.setAttribute("user", request.getParameter("user"));
		request.getRequestDispatcher("/WEB-INF/html/user.jsp").forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid user or session");
			response.sendRedirect("./Index");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
