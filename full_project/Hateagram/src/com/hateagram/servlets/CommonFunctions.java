package com.hateagram.servlets;

import javax.servlet.http.HttpServletRequest;

import com.hateagram.exceptions.BadLoginException;

public class CommonFunctions {

	public static void isLoggedIn(HttpServletRequest request) throws BadLoginException{
		if(request.getSession(false)==null || request.getSession(false).getAttribute("username")==null){
			throw new BadLoginException("You are not logged in!");
		}
	}
	
	
	
}
