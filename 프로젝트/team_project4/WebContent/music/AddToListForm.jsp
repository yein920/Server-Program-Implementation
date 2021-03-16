<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music Upload</title>
</head>
<body>
	<h3>Share Your Music</h3>
	<form action="addList.do" method="post"
		enctype="multipart/form-data">
		재생목록:<select name="playlists"><br> 
			<option value="pop">POP</option>
			<option value="kpop">K-POP</option>
			<option value="balad">발라드</option>
			<option value="dance">댄스</option>
			</select> <br>
		아티스트: <input type="text" name="artist"><br> 
		장르: 

		 
		음악: <input type="file" name="music"><br> 
		사진: <input type="file" name="photo"><br> 
		<!-- 설명:
		<textarea name="description" cols="50" rows="3"></textarea> -->
		<input type="submit" value="추가"><br>
		<!-- <onclick='location.href="list.do"> -->
	</form>
</body>
</html>