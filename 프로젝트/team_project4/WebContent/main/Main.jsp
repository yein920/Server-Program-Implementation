<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		#mainChart{
		padding:50px 100px 50px 150px;top: 5px;position: relative;height: 565px;
	}
	#images{
	width:200px;
	height:200px;
	margin:auto; 
	padding:20px;
	}
	</style>
	<script src="../js/jquery-1.12.4.min.js"></script>
	<script>
		$(document).ready(function(){
			//검색 글자 강조
			//$('img').click(function()
			//	if("${member.user_no}" != null){
			//		location.href='../music/play.do?id=' + '{music.music_id}';
			//	}else {
			//		alert('로그인해주세요');
			//	}
			//});
			
		});
	</script>
<meta charset="UTF-8">
<title>Drop The Bit!!</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<div id = 'mainChart'>
		<c:forEach var="music" items="${musics}"> <!--musics 리스트에서  music VO를하나씩 꺼냄-->
		<!--${music.title}-->
				<img id="images" src="../upload/${music.music_pic}" onclick="location.href='../music/play.do?id=${music.music_id}'"
				 	>
		</c:forEach>
	</div>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>