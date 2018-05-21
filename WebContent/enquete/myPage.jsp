<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="enquete.Java.Enquete"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// be usable session
	HttpSession loginSession = request.getSession(false);

	// judge LOGIN or LOGOUT ( areba )
	if( loginSession.getAttribute("ログイン") == null ){
		// jump jsp. and prepare.
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
		rd.forward(request, response);
	}

	// アンケート回答ページへのリンクを作る
	Enquete enq = new Enquete();
	enq.table_list(request);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MyPage</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				MyPage
					<p style="text-align: right;">
						<%= session.getAttribute("userName") %> さんがログイン中<br>
						<a href="/jApp/MyPage?log=out">ログアウト</a>
					</p>
				<form action="/jApp/EnqueteOperate" method="post">
					<ul>
						<c:forEach items="${enquete_list}" var="enquete_name">
							<li><a href="${ enquete_name.getUrl() }">${enquete_name.getEnqName()}</a>
						</c:forEach>
					</ul>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>