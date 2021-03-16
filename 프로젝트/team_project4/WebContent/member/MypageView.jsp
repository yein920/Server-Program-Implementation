<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MYPAGE</title>
<link rel="stylesheet" href="css/getSnowedInn.css">
<link rel="stylesheet" type="text/css" href="css/slick.css" />
<link rel="stylesheet" type="text/css" href="css/slick-theme.css" />

<style>
	div.container1{
		height : 500px;
		background-color : rgb(224, 224, 224);
	}
	div.container2{
		width: 60%;
		height : 500px;
		margin: 0 auto;
		background-color : rgba(255, 255, 255, 0.53);
	}

	div.subcontainer{
		display:flex;
	}

	.box_left{
		height : 400px;
		flex : 1;
	}
	.profilebox{
		width : 270px;
		height : 270px;
		margin-top : 40%;
		margin : auto 30%;
		border-radius: 50%;
	}
	
	.box_right{
		height : 400px;
		flex : 1;

	}
	.box_bottom{
		margin-bottom : 20px;
		text-align : center;
	}
	.text_line{
		border : 1px solid black;
	}
	.loc{
		margin : auto;	
		padding : 20px;
		margin-top: 32%;
	}
	
	#okbtn{
		width:120px;
		height: 40px;
		color:#fff;
		background: #585858;
		font-size: 16px;
		border:none;
		border-radius: 5px;
		box-shadow: 0 2px 4px rgb(70, 70, 70);
	}
	hr{
		margin-top : 50px;
		width :180px;
		height : 2px; 
		background-color: #777777;
		color: #777777;
		border: none; 
	}

	h2#textheader{
		text-align:center ;
	}
	
	.filelog{
		/* border : 2px solid yellow; */
		border-radius: 5px;
		box-shadow: 0 2px 4px rgb(70, 70, 70);
		left : 50%;
		text-align : center;
		margin : 50px auto;
		padding : 50px 70px;
		background-color: rgb(250, 250, 250);
		
	}
	.Tleft{
		border-left: 1px solid gold;
		border-top: 1px solid gold;
		border-bottom: 1px solid gold;
		margin-bottom : 50px;
	}
	.Tright{
		border-left: 1px solid gold;
		border-top: 1px solid gold;
		border-bottom: 1px solid gold;
		margin-bottom : 50px;
		size:40px;
	}
	
	.imgg{
		border-radius: 50%;
		
		margin-top: 85px;
	}
	td{
        width: 20%;
        padding: 12px 20px;
        margin: 8px 0;
		margin-top: 40px;
        /* display: inline-block; */
        border: 1px solid #ccc;
        border-radius: 4px;
    	/* box-sizing: border-box; */
	}

</style>
<title>나의페이지</title>
</head>
<body>
  <jsp:include page="/Header.jsp" /> 


<!--마이페이지 view 단-->

<!-- 상단 -->
	<h1  style="text-align: center;">회원 정보 수정</h1>
		<div class="container1">
			<div class="container2">
				<div class="subcontainer">
					<div class="box_left">
						<div class="profilebox">
							<input type="hidden" name="user_no" value="2" />
							<c:if test="${member.user_pic == null || member.user_pic == ''}">
								 <img src="../image/sky.png" style="width: 300px; height: 300px; " class="imgg">
							</c:if>
							<c:if test="${member.user_pic != null && member.user_pic != ''}">
								<img src="../upload/${member.user_pic}"style="width: 300px; height: 300px;" class="imgg">
							</c:if>
						</div>
					</div>
					<div class="box_right">
						<table class="loc">
							
								<tr>
									<th class=Tleft><img src="../image/userpic.PNG" style="width: 30px; height: 30px;"></th>
									<td class=Tright>${member.user_email}</td>
								</tr>
							<tr></tr>
								<tr>
									<th class=Tleft><img src="../image/nickpic.PNG" style="width: 30px; height: 30px;"></th>
									<td class=Tright>${member.user_nick}</td>
								</tr>
							
						</table>
					</div>
				</div>
			
				<div class="box_bottom">
					<input id ="okbtn" type='button' value='회원정보수정'onclick='location.href="update.do";' >
				</div>
			</div>
		</div>
		
		<!-- 하단 -->
		<hr>
		<h2 id="textheader" style="text-align: center;" > File Upload Log</h2>
		
		<table class="filelog">
			<tr>
				<th>장르</th>
				<th>제목</th>
				<th>가수</th>
				<th>발매일</th>
				<th>파일명</th>
			</tr>
			<c:forEach var="music" items="${musicList}">
				<tr>
					<td>${music.genre}</td>
					<td>${music.title}</td>
					<td>${music.artist}</td>
					<td>${music.cre_date}</td>
					<td>${music.music_id}</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="/Tail.jsp" /> 
</body>
</html>

