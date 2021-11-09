<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
	$(function name() {
		$("#btn-addCategory").click(function() {
			var name = $("#categoryName").val();
			if (name == '') {
				alert("카테고리명을 입력해 주세요.");
				return;
			}
		
			var id = '${authUser.id}';
			var desc = $("#categoryDesc").val();
			
			$.ajax({
				url: "${pageContext.request.contextPath }/blog/api/addCategory/" + id + "/" + name +"/"+ desc,
				type: "post",
				dataType: "json",
				error: function(xhr, status, e) {
					console.log(status, e)
				},
				success: function(response) {
					if (response.result != "success") {
						$("#overLap").show()
					}
					
					if (response.data) {
						$("#overLap").hide();
						location.reload(true);
					}
				}
			})
		})
	})
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/blog/admin/basic/${authUser.id}">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath }/blog/admin/write/${authUser.id}">글작성</a></li>
				</ul>
		      	<table id="admin-cat" class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<c:forEach var="vo" items="${categoryVo }" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${vo.name }</td>
							<td>${vo.count }</td>
							<td>${vo.desc }</td>
							<td><a href="${pageContext.request.contextPath}/blog/admin/category/delete/${vo.name }/${authUser.id}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
						</tr>
					</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      	
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="categoryName" type="text" name="name"></td>
		      			<td id="overLap" style="display:none;">이미 존재하는 카테고리입니다.]</td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="categoryDesc" type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btn-addCategory" type="button" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>