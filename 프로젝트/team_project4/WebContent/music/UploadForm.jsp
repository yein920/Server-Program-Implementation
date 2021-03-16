<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Music Upload</title>
<style>
	* {
		font-family: Arial, Helvetica, sans-serif;
	}
	.upload_container {
		position: relative;
		margin: 0 auto;
		width: 50%;
		height: 500px;
		padding: 50px 50px;
		top: 50px;
		line-height: 200%;
		border: 1px solid #ccc;
		background-color: #fff;
	}

	button {
		display: block;
		background-color: #ccc;
		color: #777;
		width: 160px;
		padding: 15px;
		margin: 20px;
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

	#image_container {
		position: relative;
		width: 300px;
		display: inline;
		left: 40px;
	}
	img{
		margin: 20px;
	}
	#info{
		/* height: 500px; */
		position: relative;
		left: 20px;
		float: left;
		display: inline;
	}
</style>
</head>
<body>
<jsp:include page="/Header.jsp"/>
	<form action="upload.do" method="post" enctype="multipart/form-data">
        <div class="upload_container">
            <div id="info">
            <h1>Share Your Music</h1>
            제목: <input type="text" name="title"><br>
            아티스트: <input type="text" name="artist"><br>
            장르:
            <select name="genre">
                <option value="pop">POP</option>
                <option value="kpop">K-POP</option>
                <option value="balad">발라드</option>
                <option value="dance">댄스</option>
            </select> <br>
            음악: <input type="file" name="music"><br>
            사진: <input type="file" name="photo" accept="image/*" onchange="setThumbnail(event);" />
            <br>
            <button type="submit">추가<br>
        </div>
            <div id="image_container"></div><br>
            <!-- 설명:
		<textarea name="description" cols="50" rows="3"></textarea> -->
           
                <!-- <onclick='location.href="list.do"> -->
        </div>
    </form>
    <script> function setThumbnail(event) {
            var reader = new FileReader();
            reader.onload = function (event) {
                var img = document.createElement("img");
                img.setAttribute("src", event.target.result);
                img.setAttribute("width", '400px');
                // img.setAttribute('background-size','cover');
                document.querySelector("div#image_container").appendChild(img);
            };
            reader.readAsDataURL(event.target.files[0]);
        } 
    </script>
    <jsp:include page="/Tail.jsp"/>
</body>
</html>