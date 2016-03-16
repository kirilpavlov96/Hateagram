package com.hateagram.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hateagram.DAO.MySQLUtil;

@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comment = request.getParameter("comment");
		String userID = (String)request.getSession().getAttribute("username");
		int postID = Integer.parseInt((String)request.getParameter("postID"));
		
		MySQLUtil.comment(userID, comment, postID);
		
		request.getRequestDispatcher("./Index").forward(request, response);
	}

}
