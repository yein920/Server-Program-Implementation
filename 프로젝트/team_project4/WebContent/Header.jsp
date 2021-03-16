<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
    <link rel="stylesheet" href="style.css">
<title>Effect header</title>
<style>

	a{
		color:black;
		text-weight:bold;
		text-decoration:none;
	}
	a:hover{
		font-weight:bold;
		color: #762DDD;
	}
	#top{
		position:absolute;
		left:100px;
		margin-left:-100px;
	}
	
	#top li {
 	    float: left;
	 list-style: none;
    display: inline-block;
   /* position: absolute; */
    top: 0;
    left: 100px;
    padding: 30px;
    margin-top: 60px;
     font-weight: 700;
    line-height: 80px;
    position: relative;
      font-size: 20px;

	}

	
	
	
	/* ul li{
	    float: left;
    padding: 0 30px;
    font-size: 20px;
    font-weight: 700;
    line-height: 80px;
    position: relative;
		
	}
	 */


body{
background-color:#E0DEDE; 
  margin: 0;
  padding: 0;
/*   background-color: #E0DEDE; */
  
}

	#topLogo{
  padding: 10px;
  position: absolute;
  top: 20px;
  right: 10%;
  
  
	}
.search-box{
    padding: 10px;
    position: absolute;
    top: 160px;
    right: 10%;
    transform: translate(0%,-50%);
    height: 12px;
    background-color: #fff;
    border: 2px solid #762DDD;
    border-radius: 30px;
    transition: 0.4s;
    width: 12px;
}
.search-box:hover{
  box-shadow: 0px 0px .5px 1px #762DDD;
  width: 282px;
}
.search-btn{
    text-decoration: none;
    float: right;
    width: 11px;
    height: 10px;
    background-color: #fff;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #762DDD;
}
.search-box:hover > .search-btn{
  background-color: #fff;
}

.fas {
    -moz-osx-font-smoothing: grayscale;
    -webkit-font-smoothing: antialiased;
    display: inline-block;
    font-style: normal;
    font-variant: normal;
    text-rendering: auto;
    line-height: 1;
}
.search-txt{
    display: none;
    padding: 0;
    width: 0px;
    border: none;
    background: none;
    outline: none;
    float: left;
    font-size: 1rem;
    line-height: 10px;
    transition: .4s;
}
.search-box:hover > .search-txt{
display: flex;
  width: 240px;
  padding: 0 6px;
}
#headerWhite{
background-color:white; 
height:180px;
padding:20px;
font-family: Arial, Helvetica, sans-serif;

}
</style>
 
</head>
<body>
 <div id='headerWhite'>
	<div id="topLogo">
		<a href="../main/main.do"><img style="width: 250px;"src="../image/logo_remove.png"/></a>
	</div>

	<div>
		<ul id="top">
			<li  style = "color: black;"><a href="../chart/chart.do">Chart</a></li>
			<li><a href="../music/upload.do">FileUpLoad</a></li>
			<li><a href="../member/playList.do">MyPlayList</a></li>
			<li><a href="../member/list.do">MyPage</a></li>
			<li><a href="../auth/login.do">Login</a></li>
			

		</ul>
	</div>

   <div class="search-box">
   	  <form class="search-txt" action="../search/search.do" method="post">
      	<input type="text" name="search"placeholder="What's your favorit song?" style="border:none;">
      </form>
      <div class="search-btn">
        <i class="fas fa-search"></i>
      </div>
     
    </div>
    </div>
</body>
</html>


