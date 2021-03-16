<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음악 재생 목록</title>
<style>
table {
	width: 100%;
	border-top: 1px solid #444444;
	border-collapse: collapse;
	margin-left: auto;
	margin-right: auto;
}

th, td {
	border-bottom: 1px solid #444444;
	padding: 10px;
}

h1 {
	text-align: center;
	margin-top: 70px;
}

h2 {
	text-align: center;
	margin-top: 40px;
	margin-bottom: 80px;
}

.table-container {
	margin: 0, auto;
}

tr {
	text-align: center;
	margin-right: 20px;
}

span {
	font-size: 58px;
	text-shadow: 3px 3px 3px black;
	display: inline-block;
	margin: 0;
	animation: loading .9s infinite;
}

span:nth-child(2) {
	animation-delay: .3s;
}

.intropic #introtext span:nth-child(3) {
	animation-delay: .5s;
}

@
keyframes loading { 0%, 100% {
	transform: translateY(0);
}
50%
{
transform
:
 
translateY
(10px);
}
}
</style>

</head>
<body>
	<jsp:include page="/Header.jsp" />
	<!-- <h1>MY PLAYLIST</h1> -->
	<h1>
		<span>MY</span> <span>PLAY</span><span>LIST!</span>
	</h1>
	<h2>나의 플레이리스트를 확인해보세요!</h2>

	<div class="table-container">
		
		<table style="width: 1200px;">
			<tr>
				<td></td>
				<td>제목</td>
				<td>가수</td>
				<td>장르</td>
				<td>발매일</td>
				<td>재생/정지</td>
				<td></td>
			</tr>
			
			<c:forEach var="category" items="${category }">
				<tr>
					<td colspan="7">${category }😎😎</td>
				</tr>
				
				<c:forEach var="member" items="${playList}">
					<c:if test="${category eq member.playlist_name }">
						<tr>
							<td style="text-align: center;">
								<img src="../upload/${member.music_pic}" style="width: 70px; height: 70px;">
							</td>
							<td>${member.title}</td>
							<td>${member.artist}</td>
							<td>${member.genre}</td>
							<td>${member.cre_date}</td>
							<td>
								<audio controls>
									<source src="../upload/${member.music_id}" type="audio/mp3">
								</audio>
							</td>
							<td>
								<input type='button' value='삭제' onclick='location.href="delete.do?music_id=${member.music_id}" ;'>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>	
	</div>
	<jsp:include page="/Tail.jsp" />
</body>


</html>


