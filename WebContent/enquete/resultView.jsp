<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// アンケート回答ページ以外のリンクからこのページに来た場合はマイページに飛ぶ
	if( request.getParameter("priNo") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}

	int index = 0;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アンケート結果個別表示</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				アンケート結果個別表示
				<table>
					<tr>
						<th colspan="2">質問</th><th style="min-width: 150px;">回答</th>
					</tr>
					<c:forEach items="${ qaList }" var="qa">
						<% index++; %>
						<tr>
							<td>Q<%= index %></td>
							<td>${ qa.getQue() }</td>
							<td>${ qa.getAns() }</td>
						</tr>
					</c:forEach>
				</table>
				<p style="text-align: right;">
					<a href="/jApp/enquete/resultViewAll.jsp">一覧に戻る</a>
				</p>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>