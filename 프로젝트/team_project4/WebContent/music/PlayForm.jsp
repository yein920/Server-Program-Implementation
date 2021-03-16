<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Playing - ${musicOn.title} : ${musicOn.artist}</title>
<style>
* {
	font-family: Arial, Helvetica, sans-serif;
}

div {
	/* border: 1px solid #ccc; */
	
}

.play_container {
	position: relative;
	margin: 0 auto;
	width: 70%;
	height: auto;
	padding: 50px 0px;
}

#player {
	position: relative;
	padding: 20px;
	width: 400px;
	left: 80px;
	top: 50px;
	float: left;
	display: inline;
	text-align: center;
}

audio {
	background-color: #eee;
	width: 400px;
}

#musicInfo {
	position: relative;
	left: 80px;
	padding: 5px 40px;
	top: 50px;
	width: 380px;
	height: 200px;
	display: inline-block;
}

#userInfo {
	position: relative;
	padding: 10px 30px;
	left: 80px;
	top: 50px;
	width: 400px;
	display: inline-block;
	font-size: 14px;
	font-weight: 300;
	line-height: 150%;
}

#controller {
	position: relative;
	padding: 20px 30px;
	left: 80px;
	top: 50px;
	width: 400px;
	display: inline-block;
	text-align: center;
}

button {
	background-color: #ccc;
	color: #777;
	width: 160px;
	padding: 15px;
	margin: 10px;
	border-radius: 30px;
	border: none;
	font-size: 16px;
	font-weight: 300;
}

button:hover {
	background-color: #762ddd;
	color: #fff;
	cursor: pointer;
}

#movepage {
	width: 100px;
	margin: 0;
}

p {
	font-size: 13px;
	font-weight: 100;
	margin: 10px 0;
}

#userPic {
	width: 45px;
	height: 45px;
	display: inline;
	float: left;
	margin: 0 10px;
}

#userId {
	margin: 5px 10px;
	font-weight: bolder;
}

#cre_date {
	margin: 5px 10px;
	color: #777;
	font-size: smaller;
}

#musicPic {
	width: 400px;
	height: 300px;
	background-size: cover;
	background-position: center;
	background-color: #ccc;
}

#modal {
	display: none;
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1;
	margin: 100px auto;
	margin-top: -550px;
}

#modal h2 {
	margin: 0;
}

#modal button {
	display: inline-block;
	width: 100px;
}

#modal .modal_content {
	width: 500px;
	margin: 100px auto;
	padding: 20px;
	background: #fff;
	border: 2px solid #666;
	/* vertical-align: middle; */
}

#modal .modal_layer {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	z-index: -1;
}
</style>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<form action='play.do?id=${musicOn.music_id}' method='post'><!-- playlist.do로 다시바꾸기? -->
	<div class="play_container">
		<div id="player">
			<div id="musicPic"
				style="background-image:url('../upload/${musicOn.music_pic}');"></div>
			<audio src='../upload/${musicOn.music_id}' controls='' autoplay=''></audio>
			<br>
		</div>
		<div id="musicInfo">
			<h2>${musicOn.title}</h2>
			<h3>${musicOn.artist}</h3>
			<p>그들은 피가 더운지라 실현에 대한 자신과 용기가 있다 그러므로 그들은 이상의 보배를 능히 품으며 그들의 이상은
				아름답고 소담스러운 열매를</p>
			<p>count: ${musicOn.count}</p>
		</div>
		<div id="userInfo">
			<img src='UserProfile.png' id="userPic"> 
			<span id="userId">${musicOn.user_nick}</span><br>
			<span id="cre_date">uploaded on ${musicOn.cre_date}</span><br>
		</div>
		<div id="controller">
			<button type='button' id="movepage">prev</button>
			<button type='button' id='addToList'>+add to playlist</button>
			<button type='button' id="movepage">next</button>
		</div>
	</div>
	<div id="modal">
		<div class="modal_content">
			<h2>Add to Playlist</h2>
			<p>재생목록을 선택하세요</p>
			<!--jstl써야할듯 / 우선 목록 하나만 출력해보기-->
			<input type='text' name ='playlist'/>
			<br>
			
		<!-- 	<select name='playlist'>
				<option value='플레이리스트2'>플레이리스트2</option>
				<!-- 나중에 이부분을 DB 랑 맞춰줘야함 -->
			<button type='submit' id='add'>추가</button>
			<button type='button' id='close'>취소</button>
		</div>
		<div class="modal_layer"></div>
	</div>

	<script>
		document.getElementById("addToList").onclick = function() {
			document.getElementById("modal").style.display = "block";
		}

		document.getElementById("close").onclick = function() {
			document.getElementById("modal").style.display = "none";
		}
	</script>
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>