<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<h1>${blogVo.title }</h1>
	<ul>
		<c:choose>
			<c:when test="${authUser == null }">
				<li><a href="">로그인</a></li>
			</c:when>
			
			<c:otherwise>
				<li><a href="">로그아웃</a></li>
			</c:otherwise>
		</c:choose>

		<c:if test="${authUser.id == blogVo.id }">
			<li><a href="${pageContext.request.contextPath}/blog/admin/basic">블로그 관리</a></li>
		</c:if>

	</ul>
</div>