<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<link rel="stylesheet" href="../css/hateagram.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<body>
	<div class="outer-wrapper">
		<div style="overflow: auto;">
			<div class="nav-menu">
				<nav> <a href="/Hateagram/Index"
					style="float: left; letter-spacing: -1px; font-size: 120%; font-weight: bolder;">Hateagram</a>
				<a href="/Hateagram/Logout" style="float: right;">Logout</a> <a
					href="/Hateagram/html/user.jsp" style="float: right;"> 
					<%
 						out.print((String) request.getSession(false).getAttribute("username"));
					 %>
				</a> <input id="search" type="text" name="search" placeholder="Search.." />
				</nav>

			</div>
		</div>

		<div class="inner-wrapper" style="height: 45px;"></div>

		<div class="mainContents"
			style="clear: both; width: 90%; max-width: 100%; border: 0px solid black; background-color: #fff">

			<div class="galleryUserDetails">
				<a href="img1.jpg"> <img id="img1" src="kiril.jpg"></img>
				</a>
				<h1 style="float: left">User Nick Name</h1>
				<div style="float: right; font-size: 0.8em; line-height: 0.3">
					<p>Posts:234</p>
					<p>Followers:2342</p>
					<p>Following:232</p>
				</div>
				<br />

				<div style="float: left; max-width: 50%">
					<p>Malko info za men ala bala orisjgnkrenhkrjtnh tlhltknltnlty
						ltynjltknlktnmlkt lktynmlktmnlktmn tn</p>
				</div>

				<a href="#" class="button"
					style="position: absolute; right: 10px; bottom: 10px;">Follow</a>
				<p style="clear: both; font-size: 0.6em"></p>
			</div>

			<div class="galleryPost"
				style="background-image: url(../img/img2.jpg)">
				<a href="../img/img2.jpg">
					<div class="galleryPostImg"></div>
				</a>
			</div>

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
</body>
</body>
</html>