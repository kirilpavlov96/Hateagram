<%@page import="javax.websocket.Session"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.hateagram.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page session="true"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here is my user name</title>
<link rel="stylesheet" href="css/hateagram.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
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
				</a> <input id="search" type="text" name="search" placeholder="Search.." />
				</nav>

			</div>
		</div>

		<div class="inner-wrapper" style="height: 45px;"></div>

		<div class="inner-wrapper"
			style="clear: both; border 1px solid black; background-color: #F4F6F9">

			<div class="allPostings"
				style="padding-bottom: 20px; background-color: #F4F6F9">
				<div class="post">
					<div class="postAuthor">
						<p>
							posted by:<a href="User" value="User">User</a>on:
						</p>
					</div>
					<img src="img/img1.jpg"></img>
					<div class="postActions">

						<a href="sdg"
							style="background: #ffffff url(likeIcon.bmp) no-repeat;">Like</a>
						<a href="sdg"
							style="float: right; background: #ffffff url(hateIcon.bmp) no-repeat 3px -2px;">Hate</a>

					</div>
					<div class="comment">
						<p>
							<a href="UserUserUsTYFYTavbuysaer" value="User">User1:</a> Eto go
							moqt komentar1!
						</p>
					</div>
					<p style="clear: both"></p>
				</div>
			</div>
			<%
				List<Post> l = (List<Post>) request.getSession(false).getAttribute("posts");
				for (Post p : l) {
					out.print("<div class=\"post\">");
					out.print("<div class=\"postAuthor\">");
					out.print("<p>");
					out.print("posted by:<a href=\"User\" value=\"User\">User</a>on:");
					out.print("</p>");
					out.print("</div>");
					out.print("<img src=\"img/" + p.getPath() + "\"></img>");
					out.print("<div class=\"postActions\">");
					out.print("<a href=\"sdg\"");
					out.print("	style=\"background: #ffffff url(likeIcon.bmp) no-repeat;\">Like</a>");
					out.print("<a href=\"sdg\"");
					out.print(
							"	style=\"float: right; background: #ffffff url(hateIcon.bmp) no-repeat 3px -2px;\">Hate</a>");
					out.print("</div>");
					out.print("<div class=\"comment\">");
					out.print("<p>");
					out.print("<a href=\"UserUserUsTYFYTavbuysaer\" value=\"User\">User1:</a> Eto go");
					out.print("moqt komentar1!");
					out.print("</p>");
					out.print("</div>");
					out.print("<p style=\"clear: both\"></p>");
					out.print("</div>");
				}
			%>

		</div>


		<footer class="footer">

		<div class="inner-wrapper" style="height: 45px;">
			<ul class="footer">
				<li><a href="login.html">Sign in with different account</a></li>
				<li><a href="http://www.ittalents.bg">It Talents</a></li>
				<li><a href="about.html">About us</a></li>
			</ul>
		</div>

		</footer>
	</div>
</html>