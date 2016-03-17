package com.hateagram.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hateagram.DAO.MySQLUtil;
import com.hateagram.exceptions.BadLoginException;

@WebServlet("/Dislike")
public class Dislike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			CommonFunctions.isLoggedIn(request);
			MySQLUtil.disLike((String)request.getSession(false).getAttribute("username"), Integer.parseInt(request.getParameter("post_ID")));
			response.sendRedirect("./Index");
		}
		catch(BadLoginException e){
			response.sendRedirect("./Index");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
