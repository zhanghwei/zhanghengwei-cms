<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/cms.css" rel="stylesheet">
</head>
<body>
<form action="/shou/colle">
 <input type="hidden" name="pageNum" value=1>
 </form>
<h2>我的收藏夹</h2><button onclick="add()">添加收藏</button>
<table class="table">
<c:forEach items="${pageInfo.list }" var="item">
<tr>
<td>

<a href="#">${item.text}</a><br>
时间：${item.created} <a href="/shou/del?id=${item.id }">删除</a><br>
</td>
<tr>
</c:forEach>
</table>
<jsp:include page="../common/page.jsp"></jsp:include>
<script src="/public/js/checkbox.js?v1.00"></script>
<script>
	function query(){
		var params = $("form").serialize();
		reload(params);
	}
	
	function edit(id){
		openPage("/article/add?id="+id);
	}
	
	function gotoPage(pageNo){
		$("[name=pageNum]").val(pageNo);
		query();
	}
	function add(){
		location.href="/shou/toAdd";
	}
</script>	
</html>