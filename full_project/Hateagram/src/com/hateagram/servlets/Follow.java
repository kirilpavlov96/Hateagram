package com.hateagram.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hateagram.DAO.MySQLUtil;

@WebServlet("/Follow")
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession(false).getAttribute("username");
		String following = request.getParameter("following");
		try{
			MySQLUtil.addFollowing(user, following);
			MySQLUtil.addFollower(following,user);
		}
		catch(Exception e){
			System.out.println("Unable to add this user in following!!!");
		}
		request.getRequestDispatcher("/Profile?user="+following).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
