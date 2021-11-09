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
		$("#btn-checkemail").click(function name() {
			var id = $("#id").val();
			if (id == '') {
				alert("아이디를 입력해 주세요.");
				$("#joinSubmit").attr("disabled", true);
				$("#idCheckMessage").show();
				return;
			}
			
			$.ajax({
				url: "${pageContext.request.contextPath }/user/api/checkemail?id=" + id,
				type: "get",
				dataType: "json",
				error: function(xhr, status, e) {
					console.log(status, e)
				},
				success: function(response) {
					if(response.result != "success") {
						$("#joinSubmit").attr("disabled", true);
						$("#idCheckMessage").show();
						console.error(response.message);
						return;
					}
					
					if(response.data) {
						alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
						$("#email")
							.val("")
							.focus();
						$("#joinSubmit").attr("disabled", true);
						$("#idCheckMessage").show();
						return;
					} else {
						alert("사용 가능한 아이디 입니다.");
						$("#joinSubmit").attr("disabled", false);
						$("#idCheckMessage").hide();
						
					}
				}
			});
		})
		
		$("#id").change(function(){
			$("#joinSubmit").attr("disabled", true);
			$("#idCheckMessage").show();
		})
	})
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/main/header.jsp"/>
		<form:form 
			modelAttribute="userVo"
			class="join-form" 
			id="join-form"
			method="post" 
			action="">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="text-align:left; padding-left:0; color: #f00">
				<form:errors path="name" />
			</p>
			<label class="block-label" for="blog-id">아이디</label>
			<!-- <input id="blog-id" name="id" type="text">  -->
			<form:input path="id"/>
			<p style="text-align:left; padding-left:0; color: #f00">
				<form:errors path="id" />
			</p>
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<!-- <input id="password" name="password" type="password" /> -->
			<form:input path="password"/>
			<p style="text-align:left; padding-left:0; color: #f00">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input id="joinSubmit" type="submit" value="가입하기" disabled="disabled">
			<label id="idCheckMessage" class="block-label" for="blog-id">중복체크 해주세요.</label>
		</form:form>
	</div>
</body>
</html>
