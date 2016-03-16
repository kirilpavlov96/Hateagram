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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<link rel="stylesheet" href="css/hateagram.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<body>
	<div class="outer-wrapper">
		<div style="overflow: auto;">
			<div class="nav-menu">
				<nav> <a href="Index"
					style="float: left; letter-spacing: -1px; font-size: 120%; font-weight: bolder;">Hateagram</a>
				<a href="Logout" style="float: right;">Logout</a> <a
					href="Profile" style="float: right;"> 
					<%
 						out.print((String) request.getSession(false).getAttribute("username"));
					 %>
				</a>
				</nav>
			</div>
		</div>

		<div class="inner-wrapper" style="height: 45px;"></div>

		<div class="mainContents"
			style="clear: both; width: 90%; max-width: 100%; border: 0px solid black; background-color: #fff">

			<div class="galleryUserDetails">
				<a href="img/img1.jpg"> <img id="img1" src="img/User.png"></img>
				</a>
				<h1 style="float: left"><% out.print(request.getParameter("user")); %></h1>
				<div style="float: right; font-size: 0.8em; line-height: 0.3">
					<p>Posts:<% out.print(MySQLUtil.getUserPostsCount(request.getParameter("user"))); %></p>
					<p>Followers:<% out.print(MySQLUtil.getUserFollowersCount(request.getParameter("user"))); %></p>
					<p>Following:<% out.print(MySQLUtil.getUserFollowingCount(request.getParameter("user"))); %></p>
				</div>
				<br />

				<div style="float: left; max-width: 50%">
					<p><%
					IUser user = MySQLUtil.getUser(request.getParameter("user"));
					out.println("Name: " + user.getRealName() + "<br/>");
					out.println("Age: " + user.getAge() + "<br/>");
					out.println("Email: " + user.getEmail() + "<br/>");
					%></p>
				</div>

				<a href=<%out.print("Follow?following=" + request.getParameter("user"));%>
					class="button"
					style="position: absolute; right: 10px; bottom: 10px;">
					Follow
				</a>
				<p style="clear: both; font-size: 0.6em"></p>
			</div>
			<%
				List<Post> l = MySQLUtil.getAllPostsOfUserByDate((String)request.getParameter("user"));
				for (Post p : l) {
			%>
			<div class="galleryPost"
				style="background-image: url(<%out.print("img/" + p.getFilename());%>)">
			</div>
			<%
			}
			%>
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
</body>
</body>
</html>