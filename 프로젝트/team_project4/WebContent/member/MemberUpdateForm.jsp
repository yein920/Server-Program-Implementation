<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
	<h1>회원정보수정</h1>
	<form action='update.do' method='post'>
		<!--회원번호: <input type='text' name='no' value='${member.no}' readonly><br> -->
		회원번호: <input type='text' name='no' value='${member.no}' readonly><br>
		이메일: <input type='text' name='email' value='${member.email}' readonly><br>
		<!-- 비밀번호: <input type='password' name='pw' value='${member.pw}' ><br> -->
		닉네임: <input type='text' name='nick' value='${member.nick}'><br>
		<input type='submit' value='저장'>
		
		<!-- <input type='button' value='삭제'
			onclick='location.href="delete.do?no=${member.no}";'>
		<input type='button' value='취소' onclick='location.href="playList.do"'>  -->
		
	</form>
</body>
</html>



