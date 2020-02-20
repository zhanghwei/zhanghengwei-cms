<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/cms.css" rel="stylesheet">
</head>
<body>
<form action="/admin/car/list" method="post">
<input type="hidden" name="pageNum"/>
<!--   <div class="form-group">
    <label for="exampleInputEmail1">经度</label>
    <input type="text" class="form" id="lon" >

    <label for="exampleInputPassword1">纬度</label>
    <input type="text" class="form" id="lat">
     
  </div>
  -->
 <button type="submit" class="btn btn-primary">查询</button>
</form>
<!-- <form action="/car/list" method="post">
<input type="hidden" name="pageNum"/>
经度<input type="text" name="lon" id="lon"/>-纬度<input type="text" name="lat" id="lat"/><br>
<input type="submit" value="查询"/>
</form> -->
<div class="row" style="margin-top: 15px;">
<table class="table table-dark" >
  <thead>
    <tr>
      <th scope="col"><input type="checkbox" onclick=""/> </th>
      <th scope="col">时间</th>
      <th scope="col">车牌号</th>
      <th scope="col">车型</th>
      <th scope="col">经度</th>
      <th scope="col">纬度</th>
      <th scope="col">距离</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="c">
    <tr>
      <th scope="row"><input type="checkbox" value="${c.id}"></th>
      <td>${c.created}</td> 
      <td>${c.plate}</td>
      <td>${c.type}</td>
      <td>${c.lon}</td>
      <td>${c.lat}</td>
      <td>${c.juli}</td>
    </tr>
   </c:forEach>
   
   
</tbody>
<tfoot>
<tr>

<jsp:include page="../common/page.jsp"></jsp:include>

   </tr>
</tfoot>
</table>
</div>

<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
<!-- <script type="text/javascript" src="/public/js/cms.js"></script> -->
<script type="text/javascript">

function gotoPage(pageNo){
	$("[name=pageNum]").val(pageNo);
	$("form").submit();
}
</script>
</body>
</html>