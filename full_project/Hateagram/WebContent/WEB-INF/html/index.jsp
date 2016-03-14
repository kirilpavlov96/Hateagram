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
					style="float: left; letter-spacing: -1px; font-size: 120%; font-weight: bolder;">Hateagram</a>
				<a href="Logout" style="float: right;">Logout</a> <a href=
				<% out.print("Profile?user=" + (String)request.getSession(false).getAttribute("username"));%>
					style="float: right;"> <%
 					out.print((String) request.getSession(false).getAttribute("username"));
				 %>
				</a> <input id="search" type="text" name="search" placeholder="Search.." />
				</nav>

			</div>
		</div>

		<div class="inner-wrapper" style="height: 45px;"></div>

		<div class="inner-wrapper"
			style="clear: both; border 1px solid black; background-color: #F4F6F9">
			<%
				List<Post> posts = MySQLUtil.getAllPostsUsersFollowers((String) request.getSession(false).getAttribute("username"));
				for (Post p : posts) {
			%>
			<div class="post">
				<div class="postAuthor">
					<p>
						posted by:<a href=<% out.print("Profile?user=" + p.getUserID());%>
						value="User"><%out.print(p.getUserID()); %></a> 
						date:<a><%out.print(p.getPostDate()); %></a>
					</p>
				</div>
				<br />
				<img src=<%out.print("img/" + p.getFilename());%>></img>
				<div class="postActions">
					<a href="sdg"
						style="background: #ffffff url(img/likeIcon.bmp) no-repeat;">Like</a>
					<a href="sdg"
						style="float: right; background: #ffffff url(img/hateIcon.bmp) no-repeat 3px -2px;">Hate</a>
				</div>
				<button class="showComm" >comments..</button>
				<div class="comment">
				<%
				List<Comment> comments = MySQLUtil.getAllCommentsOfPost(p);
				for (Comment c : comments) {
				%>
					<p>
						<a href=<% out.print("Profile?user=" + c.getUserID());%>>
							<% out.print(c.getUserID()+": ");%>
						</a>
						<% out.print(c.getCommentText());%>
						<% out.print(c.getPostID());%>
					</p>
				<br/>
				<%
				}
				%>
				</div>
				
				<p style="clear: both"></p>
			</div>
			<%
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
	<script type="text/javascript">
		$(document).ready(function() {

			$(".showComm").click(function() {
				$(this).next(".comment").toggle("slow");
			});
		});
	</script>
</html>