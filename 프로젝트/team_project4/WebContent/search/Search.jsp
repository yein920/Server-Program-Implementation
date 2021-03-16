<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> <!-- 함수기능사용할 수 있게 해줌 -->
<!DOCTYPE html>
<html>
<head>
	<style>
#topcontent{
width:80%;
margin: 0 auto;

}

		nav ul{
			position:relative;
			text-align:center;
			background-color:black;
			color:white;
			height:85px;
			top:-45px;
		}
		nav ul li{
			list-style-type:none;
			display:inline-block;
			padding:20px;
			margin-top:0px;
			font-size:18px;
		}
		ul li .select{
			list-style-type:none;
			display:inline-block;
			padding:20px;
			color:yellow;
		}
		ul li:hover{
			background-color:#762DDD;
		}
		#contents{
			margin:50px 300px;
		}
		table{
			border-collapse:collapse;
			position:relative;
			width:700px;
			
		}
		.contentPic1{
			margin-left:auto; margin-right:auto;
			
		}
		.content1{
			position:relative;
			left:250px;
			top:-300px;
			text-align:center;
		}
		
		td{
			border-bottom: 1px solid gray;
			padding:20px 40px 20px 40px;
		}
		th{
			border-bottom: 2px solid gray;
			padding:20px;
		}
		nav{
			position:relative;
		}
		section{
			position:relative;
			
		}
		.highlight { font-weight : bold; color:blue; }
		.containers{
			    /* padding: 50px 100px 50px 150px; */
		    top: 20px;
		    position: relative;
		    width: 100%;
		  height: 770px;
		}
		#topcontent{
			left:100px;
			position: relative;
			left:150px;
		}
	</style>
	<script src="../js/jquery-1.12.4.min.js"></script>
	<script>
		$(document).ready(function(){
		
			var $order = $(".one");
			var category = null;
			
			//검색 글자 강조
			if("${category.category}" == "title"){
				category = ".one";
				//통합검색과 곡검색은 둘다 곡명이 첫번째
			}else if("${category.category}" == "artist"){
				category = ".two";
				$order = $(".two");
			}else if("${category.category}" == "genre"){
				category = ".three";
				$order = $(".three");
			}else if("${category.category}" == "cre_date"){
				category = ".four";
				$order = $(".four");
			}
			
			//$(".two").insertBefore(".one"); //영어문장 어순처럼
		});
	</script>
<meta charset="UTF-8">
<title>Search Result</title>
</head>
<body>
<!-- 가수명검색,제목검색,장르 -->
	<jsp:include page="/Header.jsp"/>
	<div class="containers">
	<nav>
		<ul>
			<li onclick="location.href='search.do?search=${search.search}'">통합 검색</li>
			<li onclick="location.href='search.do?search=${search.search}&category=title'">곡 검색</li>
			<li onclick="location.href='search.do?search=${search.search}&category=artist'">가수 검색</li>
			<li onclick="location.href='search.do?search=${search.search}&category=genre'">장르 검색</li>
			<li>
				<form action="search.do">
					<input type="text" name="search" placeholder="${search.search}">
					<input type="submit" value="검색">
				</form>
			</li>
			<li onclick="location.href='../main/main.do'">홈으로</li>
		</ul>
	</nav>
	
	<section>
			<c:choose>
				<c:when test="${category.category == 'title'}">
					<table style="margin-left:auto; margin-right:auto; text-align:center;">
					<tr>
						<th class="one" style="color:purple">곡 명</th>
						<th></th>
						<th class="two">가수</th>
						<th class="three">장르</th>
						<th class="four">발매일</th>
						<th>듣기</th>
					</tr>
					<c:set var="spanSearch" value="<span class='highlight'>${search.search}</span>"/> 
					
					<c:forEach var="result" items="${searchResult}">
					<tr id="sector">
						<td class="one" style="font-weight:bold;">${fn:replace(result.title, search.search, spanSearch)}</td>
						<td><img src="../upload/${result.music_pic}" width="130px" height="130px"></td>
						<td class="two">${result.artist}</td>
						<td class="three">${result.genre}</td>
						<td class="four">${result.cre_date}</td>
						<td class="menu" onclick="location.href='../music/play.do?id=${result.music_id}'">
							<i class="far fa-play-circle" style="font-size:40px; color:#762DDD;"></i>
						</td>
					</tr>
					</c:forEach>
					</table>
				</c:when>
			
				<c:when test="${category.category == 'artist'}">
				
					<div id="topcontent">
						<c:set var="beforeValue" value="nothing"/>
						<c:forEach var="result" items="${searchResult}">
							<c:if test="${result.artist != beforeValue}">
								<c:set var="beforeValue" value="${result.artist}"/>
								<img src="../upload/${result.artist}.jpg" width="200px" height="250px">
								<c:set var="spanSearch" value="<span class='highlight'>${search.search}</span>"/> 
								<h3 style="font-weight:bold; margin:0px; width:200px; text-align:center;">${fn:replace(result.artist, search.search, spanSearch)}</h3>
							</c:if>
							<table class="content1">
								<tr id="sector">
									<td style="font-weight:bold">발매곡</td>
									<td class="one">${result.title}</td>
									<td class="three">${result.genre}</td>
									<td class="four">${result.cre_date}</td>
									<td class="menu" onclick="location.href='../music/play.do?id=${result.music_id}'">
										<i class="far fa-play-circle" style="font-size:40px; color:#762DDD;"></i>
									</td>
								</tr>
							</table>
						</c:forEach>
					</div>
				</c:when>
				
				<c:when test="${category.category == 'genre'}">
				<table style="margin-left:auto; margin-right:auto; text-align:center; width:800px;">
					<tr>
						<th></th>
						<th class="three" style="color:purple">장르</th>
						<th class="one">곡 명</th>
						<th class="two">가수</th>
						<th class="four">발매일</th>
						<th>듣기</th>
					</tr>
					
					<c:set var="spanSearch" value="<span class='highlight'>${search.search}</span>"/> 
					<c:forEach var="result" items="${searchResult}">
					<tr id="sector">
						<td> <img src="../upload/${result.genre}.jpg" width="150px" height="150px"></td>
						<td class="three" style="font-weight:bold;">${fn:replace(result.genre, search.search, spanSearch)}</td>
						<td class="one">${result.title}</td>
						<td class="two">${result.artist}</td>
						<td class="four">${result.cre_date}</td>
						<td class="menu" onclick="location.href='../music/play.do?id=${result.music_id}'">
							<i class="far fa-play-circle" style="font-size:40px; color:#762DDD;"></i>
						</td>
					</tr>
					</c:forEach>
				</table>
				</c:when>
				
 				<c:otherwise>
 				<table style="margin-left:auto; margin-right:auto; text-align:center;">
					<tr>
						<th class="one">곡 명</th>
						<th class="two">가수</th>
						<th class="three">장르</th>
						<th class="four">발매일</th>
						<th>듣기</th>
					</tr>
					
					<c:set var="spanSearch" value="<span class='highlight'>${search.search}</span>"/> 
					<c:forEach var="result" items="${searchResult}">
					<tr id="sector">
						<td class="one">${fn:replace(result.title, search.search, spanSearch)}</td>
						<td class="two">${fn:replace(result.artist, search.search, spanSearch)}</td>
						<td class="three">${fn:replace(result.genre, search.search, spanSearch)}</td>
						<td class="four">${fn:replace(result.cre_date, search.search, spanSearch)}</td>
						<td class="menu" onclick="location.href='../music/play.do?id=${result.music_id}'">
							<i class="far fa-play-circle" style="font-size:40px; color:#762DDD;"></i>
						</td>
					</tr>
					</c:forEach>
				</table>
				</c:otherwise>
			</c:choose>
	</section>
	</div>
	
</body>
</html>
<!--변수를 생성하여 검색값을 span태그로 감싸서 여기에 넣는다. -->