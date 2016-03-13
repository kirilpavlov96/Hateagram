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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(MySQLUtil.isUserExisting(username, password)) {
			HttpSession httpSession= request.getSession(true);
			httpSession.setAttribute("username", username);
			httpSession.setMaxInactiveInterval(15*60);
			
			//getting all posts
			System.out.println(MySQLUtil.getAllPosts().size());
			httpSession.setAttribute("posts", MySQLUtil.getAllPosts());
			
			
			response.sendRedirect("/Hateagram/html/index.jsp");
		}
		else{
			response.sendRedirect("/Hateagram/html/login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
