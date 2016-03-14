package com.hateagram.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null || (String)session.getAttribute("username")==null){
			request.getRequestDispatcher("/WEB-INF/html/login.html").forward(request, response);
			//response.sendRedirect("/Hateagram/html/login.html");
		}
		else{
		    request.getRequestDispatcher("/WEB-INF/html/index.jsp").forward(request, response);
			//response.sendRedirect("/Hateagram/html/index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}