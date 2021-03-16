<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>로그인</title>
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

.login-Header h2{
	margin-top: 45px;
	color:black;
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
.login-input-section-wrap{
	padding-top: 30px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.login-input-wrap{
border : 1px;
	width: 465px;
	height :48px;
/* 	border: solid 1px ; */
	background: white;
}
.password-wrap{
	margin-top: 5px;
}
.login-input-wrap input{
	border: none;
	width:430px;
	margin-top: 10px;
	font-size: 14px;
	margin-left: 10px;
	height:30px;
}
.login-button-wrap {
	padding-top: 13px;
	text-align : center;
	
	
}
.login-button-wrap #enjoy{
	width: 465px;
	height :48px;
	font-size: 18px;
	background: #762DDD;
	color: white;
	border: solid 1px #762DDD;
	flex-direction: column;
	font-style: italic;
	padding: 13px 30px 0px 200px;
	
}

.join-button-wrap {
	padding-top: 5px;
}
.join-button-wrap #join{
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
	color : black;


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
	<form action="login.do" method="post">
	<div class="main-container">
			
		 <div class="login-Header">
		 	<h2>LOGIN</h2>
		 </div>
		 
		<section class="login-input-section-wrap">
			<div class="login-input-wrap">	
				<input placeholder="User email" type="text" name="user_email"></input>
			</div>
			<div class="login-input-wrap password-wrap">	
				<input placeholder="Password" type="password" name="user_pw"></input>
			</div>
			<div class="login-button-wrap">
			<input id='enjoy' type="submit" value=" Enjoy ! ">
			</div>
			<div class="join-button-wrap">
			<a href='<%=request.getContextPath()%>/member/add.do'><input id='join' type=button value="Do you wanna JOIN us ?"></a>
			</div>

		</section>
 
		 
		<footer>
			<div class="copyright-wrap">
			<span>Copyright © BitCamp Corp. All Rights Reserved.</span>
			</div>
		</footer>
		
		</div>
	</div>
	</form>
	<jsp:include page="/Tail_Login.jsp" />
</body>
</html>




