<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body> 
	<jsp:include page="/Header.jsp" />
	<h1>회원 목록</h1>
	<p><a href='list.do'>신규 회원</a></p>
	<c:forEach var="member" items="${members}">
		${member.music_id},
		${member.user_no},
		${member.genre},
		${member.title},
		${member.cre_date},
		${member.artist},
		${member.count}
		<br/>
	</c:forEach>
	<jsp:include page="/Tail.jsp" />
</body>
</html>




<!-- 		<a href='update.do?no=${member.no}'>${member.name}</a>,
		${member.email},
		${member.createdDate}
		<a href='delete.do?no=${member.no}'>[삭제]</a>  -->
