<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="2;url=login.do"> -->
<title>로그인 실패</title>
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
.main-container  .main-wrap {
	width:768px;

}
.main-container .main-wrap  
{
	width: 96px;
	height: 30px;
	color: var(--font-color);
	border: solid 1px var(--border-gray-color);
}
.main-container  .main-wrap .logo-wrap{
	padding-top:55px;
}
.main-container .main-wrap.logo-wrap img
{
	width: 231px;
	height: 44px;
}

.join-Header h2{
	margin-top : 45px;
}

.main-container  .main-wrap header  {
	display:flex;
	justify-content:flex-end;
}

.main-container  .main-wrap header .logo-wrap{
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
 	align-items: center;
	width: 465px;
	height :48px;
/*  border: solid 1px ;   */
 	flex-direction: column;
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
.join-button-wrap #join{
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

.relogin-button-wrap {
	padding-top: 5px;
}
.relogin-button-wrap #relogin{
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
<body>
	<jsp:include page="/Header.jsp" />
 
	<form action="add.do" method="post">
	<div class="main-container">
			<div class="logo-wrap">
				<img src="../image/logo_remove.png">
			</div>
		 <div class="join-Header">
		  <h2>아이디 또는 비밀번호가 맞지 않아요</h2>
		 </div>
		<section class="join-input-section-wrap">
			<!-- <div class="join-input-wrap">	
				 <h2>아이디 또는 비밀번호가 맞지 않아요</h2>
			</div> -->
			
			<div class="join-button-wrap">
			<a href='<%=request.getContextPath()%>/member/add.do'><input id='join' type="button" value=" Join us "></a>
			</div>
			
			<div class="relogin-button-wrap">
			<a href='<%=request.getContextPath()%>/auth/login.do'><input id='relogin' type='button' value='Relogin'></a>
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






