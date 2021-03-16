<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="style.css">
<style>
nav {
	background-color: black;
	color: white;
	height: 50px;
}

ul, li {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
}

ul.myMenu {
	margin-left: 300px;
	height: 120px;
	width: 900px;
}

ul li.subcategory {
	height: 20px;
}

ul.myMenu>li {
	display: inline-block;
	width: 80px;
	text-align: center;
	position: relative;
	top: -36px;
	left: -270px;
	font-size: 14px;
}

ul li.menu {
	padding-top: 10px;
	margin-top: 41px;
	left: -135px;
}

ul.myMenu>li ul.submenu1 {
	display: none;
	position: absolute;
	top: 29px;
	left: 0px;
	font-size: 14px;
	background-color:#762DDD;
	width:80px;
}

ul.myMenu>li ul.submenu2 {
	display: none;
	position: absolute;
	top: 42px;
	left: 10px;
	font-size: 14px;
}

ul.myMenu>li.menu:hover {
	background-color: #762DDD;
	padding-bottom: 16px;
}

ul.myMenu>li:hover ul.submenu1 {
	display: block;
}

ul.myMenu>li:hover ul.submenu2 {
	display: block;
}

ul.myMenu>li ul.submenu>li {
	display: inline-block;
	width: 80px;
	paddng: 5px 10px;
	text-align: center;
	height: 30px;
}

ul.myMenu>li.submenu1>li:hover {
	background-color: #762DDD;
	padding-bottom: 16px;
}

ul.myMenu li.menu ul.submenu1 li.subCategory1:hover {
	color: black;
}

ul.myMenu>li.menu:hover {
	background-color: #762DDD;
}

table {
	border-collapse: collapse;
	padding: 20px 20px;
}

td {
	padding: 20px;
	border-bottom: 1px solid #444444;
	color: black;
}

th {
	padding: 20px;
	border-bottom: 1px solid #444444;
	color: black;
}

section {
	background: #E0DEDE;
}

.contentBlock {
	padding: 30px;
}

.subCategory1 {
	margin-top: 15px;
	height: 30px;
}

.subCategory2 {
	margin-top: 0px;
	height: 0px;
}

.genreCategory {
	height: 200px;
}

.artistCategory {
	height: 100px;
}
</style>
<script src="../js/jquery-1.12.4.min.js"></script>
<script>
		$(document).ready(function(){
			//검색 글자 강조
			if("${chartInput.artist}" != ""){
				$("#two").css({color:"#762DDD"});
			}else if("${chartInput.genre}" != ""){
				$("#three").css({color:"#762DDD"});
			}else if("${chartInput.ranking}" == "1"){
				$("#four").css({color:"#762DDD"});
			}
			
		});
	</script>
<meta charset="UTF-8">
<title>chart</title>
</head>
<body>
	<jsp:include page="/Header.jsp" />
	<nav>
		<ul class="myMenu">
			<li class="menu" onclick="location.href='chart.do'">종합 순위</li>
			<li class="menu" id="genreCategory">장르별
				<ul class="submenu1">
					<li class="subCategory1"
						onclick="location.href='chart.do?genre=kpop'">kpop</li>
					<li class="subCategory1"
						onclick="location.href='chart.do?genre=pop'">pop</li>
					<li class="subCategory1"
						onclick="location.href='chart.do?genre=balad'">balad</li>
				</ul>
			</li>

			<li class="menu" id="artistCategory">가수별
				<ul class="submenu2">
					<li class="subCategory2">
						<form action="chart.do" method="post" id="subCategory2Form">
							<input type="text" name="artist"> <input type="submit"
								value=""
								style="background-color: transparent; border: 0px transparent solid;">
						</form>
					</li>
				</ul>
			</li>
			<li class="menu" onclick="location.href='chart.do?ranking=1'">최신음악별</li>
			<li class="menu" onclick="location.href='../main/main.do'">홈으로</li>
		</ul>
	</nav>
	<section>
		<div class="contentBlock">
			<table
				style="margin-left: auto; margin-right: auto; text-align: center;">
				<tr>
					<th>순위</th>
					<th></th>
					<th id="one">곡 제목</th>
					<th id="two">가수</th>
					<th id="three">장르</th>
					<th id="four">발매일</th>
					<th>재생 횟수</th>
					<th>듣기</th>
				</tr>
				<c:forEach var="music" items="${chart}">
					<tr class="contentMusic">
						<td>${music.ranking}</td>
						<td><img src="../upload/${music.music_pic}" width="100px"
							height="100px" style="border: 1px solid black;" /></td>
						<td id="one">${music.title}</td>
						<td id="two">${music.artist}</td>
						<td id="three">${music.genre}</td>
						<td id="four">${music.cre_date}</td>
						<td>${music.count}</td>
						<td class="menu"
							onclick="location.href='../music/play.do?id=${music.music_id}'">
							<i class="far fa-play-circle"
							style="font-size: 40px; color: #762DDD;"></i>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
	<jsp:include page="/Tail.jsp" />
</body>
</html>