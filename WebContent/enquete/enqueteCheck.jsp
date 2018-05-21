<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% int qNum = 0; %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アンケート確認ページ</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				Question  check
				<table>
					<tr>
						<th colspan="2">質問</th><th style="min-width: 150px;">回答</th>
					</tr>
					<c:forEach items="${ qList }" var="q">
						<%
							qNum++;
							String qAnswer = "qAnswer" + qNum;

						%>
						<tr>
							<td>Q<%=qNum %></td>
							<td>${ q }</td>
							<td><%= session.getAttribute(qAnswer) %></td>
						</tr>
					</c:forEach>
				</table>
				<form action="/jApp/EnqueteOperate" method="post">
					<p>
						<input type="hidden" name="ans" value="send">
						<input type="submit" value="送信">
					</p>
				</form>
			</div>
			<br>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>