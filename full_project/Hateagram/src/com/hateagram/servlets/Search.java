package com.hateagram.servlets;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hateagram.DAO.MySQLUtil;
import com.hateagram.exceptions.BadLoginException;
import com.hateagram.model.IUser;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			CommonFunctions.isLoggedIn(request);
			request.getRequestDispatcher("WEB-INF/html/userSearch.jsp?searchKey="+request.getParameter("searchKey")).forward(request, response);
		}
		catch(BadLoginException e){
			e.printStackTrace();
			System.out.println("Ivalid login");
			response.sendRedirect("./Index");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
