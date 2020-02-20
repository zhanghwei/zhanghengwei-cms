<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<form>
<table class="table">
<tr>
<td>收藏夹文本：<input type="text" name="text" id="text"/></td>
<td>url地址：<input type="text" name="url" id="url"/></td>
<td>用户id：<input type="text" name="user_id" id="user_id"/></td>
<td>收藏时间：<input type="date" name="created" id="created"/></td>
</tr>
</table>
</form>
<button onclick="add()">添加</button>
<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
<script type="text/javascript">

function add(){
	var param=$("form").serialize();
	$.post(
	"/shou/add",
	param,
	function(res){
		alert(res);
		if(res){
			alert("收藏成功");
		}else{
			alert("收藏失败或url非法地址");
		}
	}
	
	)
	
	
}
</script>
</body>
</html>