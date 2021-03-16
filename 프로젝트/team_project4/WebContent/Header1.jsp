<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<style>
	a{
		color:white;
		text-decoration:none;
	}
	a:hover{
		font-weight:bold;
		color: yellow;
	}
	ul{
		position:absolute;
		left:100px;
	}
	ul li{
		display:inline-block;
		list-style-type:none;
		padding:20px;
		
	}
	#search{
		position:absolute;
		right:50px;
		top:150px;
	}
</style>

<div style="background-color:black; color:white; text-align:center;
		height:150px; padding:20px;">
	<h1>DROP THE BIT!!</h1>
	<div>
		<ul>
			<li><a href="../chart/chart.do">Chart</a></li>
			<li><a href="../music/upload.do">FileUpLoad</a></li>
			<li><a href="../member/list.do">MyPage</a></li>
			<li><a href="../auth/login.do">Login</a></li>
		</ul>
	</div>
	<div id="search">
		<form action="../search/search.do" method="post">
			<input type="text" name="search">
			<input type="submit" value="" style="background-color:transparent; border:0px transparent solid;">
		</form>
	</div>	
</div>
