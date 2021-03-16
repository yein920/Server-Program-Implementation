<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MYPAGE</title>
<link rel="stylesheet" href="css/getSnowedInn.css">
<link rel="stylesheet" type="text/css" href="css/slick.css" />
<link rel="stylesheet" type="text/css" href="css/slick-theme.css" />

<style>
.container1 {
	height: 800px;
	text-align: center;
	background-color: rgb(224, 224, 224);;
}

.imgBox {
	margin: 35px 0;
	
}
img{
border-radius: 70%;}

#h1Tag {
	text-align: center;
}

.inputBox {
	text-align: center;
}

.profile {
	width: 100%;
	height: 100%;
	object-fit: cover;
	border-radius: 50%;
	box-shadow: 2px 2px rgb(70, 70, 70);
}

#updatebtn {
	width: 140px;
	height: 40px;
	color: #fff;
	background: #585858;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgb(70, 70, 70);
	margin-top: 35px;
}

input {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}
</style>
<title>나의페이지</title>
</head>
<body>
	<jsp:include page="/Header.jsp" />
	<h1 id="h1Tag">회원 정보 수정</h1>
	<div class="container1">
		<form action="update.do" method="post" enctype="multipart/form-data">
			<input type='hidden' name='user_no' value='${member.user_no}'><br>
			<div class="imgBox">
				<c:if test="${member.user_pic == null }">
					<img src="../image/sky.png" style="width: 270px; height: 270px;">
				</c:if>
				<c:if test="${member.user_pic != null }">
					<img src="../upload/${member.user_pic}" style="width: 270px; height: 270px;">
				</c:if>
				<br> <input type="file" name="user_pic">
			</div>
			<div class="inputBox">
				이메일<br> 
				<input type='text' name='user_email' value='${member.user_email}' readonly style="border-radius: 2px;" id="fname"><br> 
				닉네임<br> 
				<input type='text' name='user_nick' value='${member.user_nick}' style="border-radius: 2px;"><br> 
				비밀번호<br> <input type='password' name='user_pw' value='${member.user_pw}' style="border-radius: 2px;"><br>
			</div>
			<div class="btnBox">
				<input type='submit' value='회원정보수정' id="updatebtn">
			</div>
		</form>
	</div>
	<jsp:include page="/Tail.jsp" />
</body>
</html>