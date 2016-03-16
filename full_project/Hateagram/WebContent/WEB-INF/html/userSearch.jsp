<%@page import="javax.websocket.Session"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.hateagram.model.*"%>
<%@page import="com.hateagram.DAO.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page session="true"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here is my user name</title>
<link rel="stylesheet" href="css/hateagram.css" />
<link rel="stylesheet" href="css/w3.css">
<script src="js/jquery-2.2.1.min.js"
	integrity="sha256-gvQgAFzTH6trSrAWoH1iPo9Xc96QxSZ3feW6kem+O00="
	crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<div class="outer-wrapper">
		<div style="overflow: auto;">

			<div class="nav-menu">
				<nav> <a href="Index"
					style="float: left; letter-spacing: -1px; font-size: 120%; font-weight: bolder;">
					Hateagram </a> <a href="Logout" style="float: right;">Logout</a> <a
					href=<%out.print("Profile?user=" + (String) request.getSession(false).getAttribute("username"));%>
					style="float: right;"> <%
 	out.print((String) request.getSession(false).getAttribute("username"));
 %>
				</a>
				</nav>

			</div>
		</div>

		<div class="inner-wrapper" style="height: 45px;"></div>
		
		<div class="inner-wrapper"
			style="clear: both; border 1px solid black; background-color: #F4F6F9">
				<ul class="w3-ul w3-card-4">
					<%
						List<IUser> users = MySQLUtil.searchForUsers(request.getParameter("searchKey"));
						for(IUser user : users){
					%>
							  <li class="w3-padding-16">
							    <img src="img/img1.jpg" class="w3-left w3-circle" style="width:60px">
							    <span class="w3-xlarge" style="margin-left: 10px;">
							    	<a href=<%out.print("Profile?user=" + user.getUserName());%>>
							    		 <%out.print(user.getUserName()); %>
							    	</a>
							    </span>
							    <br>
							    <span>Name: <%out.print(user.getRealName()); %></span>
							    <br>
							    <span>Email: <%out.print(user.getEmail()); %></span>
							  </li>
					<%
						}
					%>
				</ul>
		</div>

		<footer class="footer">

		<div class="inner-wrapper" style="height: 45px;">
			<ul class="footer">
				<li><a href="http://www.ittalents.bg">It Talents</a></li>
				<li><a href="">About us</a></li>
			</ul>
		</div>

		</footer>
	</div>
</html>