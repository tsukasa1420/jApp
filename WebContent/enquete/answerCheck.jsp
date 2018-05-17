<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<th>質問</th><th>回答</th>
					</tr>
					<c:forEach items="${ qList }" var="q">
						<tr>
							<td>${ q }</td>
							<td>aaaaa</td>
						</tr>
					</c:forEach>
				</table>
				<form action="/jApp/Answer" method="post">
					<p>
						<input type="hidden" name="ans" value="check">
						<input type="submit" value="送信">
					</p>
				</form>
			</div>
			<br>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>