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
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
<script src="js/css/bootstrap.min.js"
	integrity="sha256-gvQgAFzTH6trSrAWoH1iPo9Xc96QxSZ3feW6kem+O00="
	crossorigin="anonymous"></script>
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
				<a href="Logout" style="float: right;">Logout</a> <a
					href=<%out.print("Profile?user=" + (String) request.getSession(false).getAttribute("username"));%>
					style="float: right;"> <%
 	out.print((String) request.getSession(false).getAttribute("username"));
 %>
				</a> 
				<form action="Search">
					<input id="search" type="text" name="searchKey" placeholder="Search.." />
				</form>
				</nav>

			</div>
		</div>

		<div class="inner-wrapper" style="height: 45px;"></div>

		<div class="inner-wrapper"
			style="clear: both; border 1px solid black; background-color: #F4F6F9">
			<form style="margin-left: 10%; margin-bottom: 10px;" action="Upload" method="post" enctype="multipart/form-data">
				<lable>Post : </lable>
                <input style="width: 70%;" type="file" name="file" />
                <input style="width: 10%;" type="submit" value="upload" />
            </form> 
			<%
				List<Post> posts = MySQLUtil
						.getAllPostsUsersFollowers((String) request.getSession(false).getAttribute("username"));
				for (Post p : posts) {
			%>
			<div class="post">
				<div class="postAuthor">
					<p>
						posted by:
						<a href=<%out.print("Profile?user=" + p.getUserID());%> value="User"> 
							<%
 								out.print(p.getUserID());
 							%>
						</a> 
						date:
						<a> 
							<% 
								out.print(p.getPostDate());
							%>
						</a>
					</p>
				</div>
				<br /> <img src=<%out.print("img/" + p.getFilename());%>></img>
				<div class="postActions">
					<a href=<%out.print("Like?post_ID=" + p.getID());%>
						style="background: #ffffff url(img/likeIcon.bmp) no-repeat;">
						Like :
						<%
							out.print(MySQLUtil.getPostLikes(p));
						%>
					</a> 
					<a href=<%out.print("Dislike?post_ID=" + p.getID());%>
						style="float: right; background: #ffffff url(img/hateIcon.bmp) no-repeat 3px -2px;">
						Hate :
						<%
 							out.print(MySQLUtil.getPostDisLikes(p));
 						%>
					</a>
				</div>
				<button class="showComm btn-default">comments..</button>
				<div class="comment">
					<%
						List<Comment> comments = MySQLUtil.getAllCommentsOfPost(p);
							for (Comment c : comments) {
					%>
					<p>
						<a href=<%out.print("Profile?user=" + c.getUserID());%>> 
							<%
 								out.print(c.getUserID() + ": ");
 							%>
						</a>
						<%
							out.print(c.getCommentText());
						%>
					</p>
					<br />
					<%
						}
					%>
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group">
								<form action="Comment" method="post">
									<input type="text" class="form-control" name="comment" placeholder="Comment...">
									<input type="hidden" name="postID" value="<%out.print(p.getID()); %>" />
									<span class="input-group-btn">
										<input type="submit" class="btn btn-default" type="button" value="Submit!">
									</span>
								</form>
							</div>
						</div>
					</div>
					<br />
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
					<li><a href="http://www.ittalents.bg">It Talents</a></li>
					<li><a href="">About us</a></li>
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