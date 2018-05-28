<%@page import="enquete.Java.Enquete" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ログイン済みかを判定・未ログインであればログインページへ飛ぶ
	if( session.getAttribute("ログイン") == null ){
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
		<title>マイページ</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				マイページ
				<div style="position: relative; height: 4em;">
					<p style="position: absolute; right: 0; width: 180px; text-align: left;">
						<%= session.getAttribute("userName") %> さんがログイン中<br>
						<a href="/jApp/ResultOperate">過去の解答を見る</a><br>
						<a href="/jApp/enquete/enqueteMake.jsp">アンケート作成</a><br>
						<a href="/jApp/enquete/userInfo.jsp">ユーザー情報ページ</a><br>
						<a href="/jApp/MyPage?log=out">ログアウト</a>
					</p>
				</div>
				回答できるアンケート一覧
				<form action="/jApp/EnqueteOperate" method="post">
					<ul>
						<c:forEach items="${enquete_list}" var="enquete_name">
							<li><a href="${ enquete_name.getEnqURL() }">${ enquete_name.getEnqName() }</a>
						</c:forEach>
					</ul>
				</form>
				<% request.setAttribute("imageLink", "ans.png"); %>
				<jsp:include page="imageLink.jsp"></jsp:include>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>