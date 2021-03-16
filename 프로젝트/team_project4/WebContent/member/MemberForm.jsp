<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>회원가입</title>
 <head> 
<style>

*{
	margin:0;
	padding:0;
}

body{
	background:#E0DEDE;
	font-family: Arial, Helvetica, sans-serif;
}

.main-container{
	width:100%;
	display:flex;
	flex-direction:column;
	align-items:center;
	margin-top: 21px;
}
.main-container .main-wrap{
	width:768px;

}
.main-container .main-wrap 
{
	width: 96px;
	height: 30px;
	color: var(--font-color);
	border: solid 1px var(--border-gray-color);
}
.main-container .main-wrap .logo-wrap{
	padding-top:55px;
}
.main-container .main-wrap .logo-wrap img
{
	width: 231px;
	height: 44px;
}

.join-Header h2{
	margin-top : 45px;
}

.main-container .main-wrap header {
	display:flex;
	justify-content:flex-end;
}

.main-container .main-wrap header .logo-wrap{
	display:flex;	
	flex-direction: column;
	align-items: center;
}
.join-input-section-wrap{
	padding-top: 30px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.join-input-wrap{
border : 1px;
	width: 465px;
	height :48px;
/* 	border: solid 1px ; */
	background: white;
	margin-top: 5px;
}
.join-wrap{
	margin-top: 13px;
}
.join-input-wrap input{
	border: none;
	width:430px;
	margin-top: 10px;
	font-size: 14px;
	margin-left: 10px;
	height:30px;
}
.join-button-wrap {
	padding-top: 13px;
}
.join-button-wrap #friend{
	width: 465px;
	height :48px;
	font-size: 18px;
	background: #762DDD;
	color: white;
	border: solid 1px #762DDD;
	flex-direction: column;
	font-style: italic;
	padding: 13px 30px 0px 130px;
}

.cancel-button-wrap {
	padding-top: 5px;
}
.cancel-button-wrap #cancel{
	 width: 465px;
	height :48px;
	font-size: 18px;
	background: #762DDD;
	color: white;
	border: solid 1px #762DDD;	 
	flex-direction: column;
	font-style: italic;
	padding: 13px 30px 0px 135px;
 
}

  
footer{
   
	padding-top: 95px;
	padding-bottom: 15px;

	display:flex;
	flex-direction:column;
	align-items:center;
	width:768px;



}
.copyright-wrap{

	display: flex;
	flex-direction: column;
	align-items: center;
	height: 15px;


}
footer .copyright-wrap span img{
	width: 59px;
	
	height: 11px;
}
footer .copyright-wrap span{
	font-size: 13px;
	line-height: 15px;
}
 </style>
 
</head>
 	<jsp:include page="/Header.jsp" />
<body>
	<form action="add.do" method="post">
	<div class="main-container">
			<div class="logo-wrap">
				<img src="../image/logo_remove.png">
			</div>
		 <div class="join-Header">
		 	<h2>JOIN</h2>
		 </div>
		<section class="join-input-section-wrap">
			<div class="join-input-wrap">	
				<input placeholder="email" type="text" name="user_email"></input>
			</div>
			<div class="join-input-wrap password-wrap">	
				<input placeholder="Password" type="password" name="user_pw"></input>
			</div>
			
			<div class="join-input-wrap nickname-wrap">	
				<input placeholder="Nickname" type="text" name="user_nick"></input>
			</div>
			
			<div class="join-button-wrap">
			<input id='friend' type="submit" value=" Finally! We are Friend ! ">
			</div>
			
			<div class="cancel-button-wrap">
			<a href='<%=request.getContextPath()%>/auth/login.do'><input id='cancel' type='button' value='BYE! see you next time '></a>
			</div>

		</section>
 
		 
		<footer>
			<div class="copyright-wrap">
			<span>Copyright © BitCamp Corp. All Rights Reserved.</span>
			</div>
		</footer>
		
		</div>

	</form>
	<jsp:include page="/Tail.jsp" />
</body>
</html>




