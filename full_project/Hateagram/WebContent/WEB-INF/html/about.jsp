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

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<div class="outer-wrapper">
		<div style="overflow: auto;">

			<div class="nav-menu">
				<nav> <a href="Index"
					style="float: left; letter-spacing: -1px; font-size: 120%; font-weight: bolder;">Hateagram</a>
				<a href="Logout" style="float: right;">Logout</a> <a
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
			<div class="allPostings"
				style="padding-bottom: 20px; background-color: #F4F6F9">

				<div class="post">

					<br></br>
					<lable style="margin-left: 10%;">Sia Simeonova</lable>
					<hr>
					<img src="img/sia.png" style="width: 200px; height: 200px;"></img> <br></br>

					<lable style="margin-left: 10%;">Kiril Pavlov</lable>
					<hr>
					<img src="img/kiril.jpg" style="width: 200px; height: 200px;"></img> <br></br>
					
					<lable style="margin-left: 10%;">Teodor Marinov</lable>
					<hr>
					<img src="img/teodor.jpg" style="width: 200px; height: 200px;"></img>

					<p style="clear: both"></p>
				</div>
			</div>


			<footer class="footer">

			<div class="inner-wrapper" style="height: 45px;">
				<ul class="footer">
					<li><a href="http://www.ittalents.bg">It Talents</a></li>
				</ul>
			</div>

			</footer>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {

				$(".showComm").click(function() {
					$(this).next(".comment").toggle("slow");
				});
			});
		</script>
</html>