<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アンケート回答</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				Question
				<form action="/jApp/Answer" method="post">
					<table>
						<tr>
							<th>質問</th><th>回答</th>
						</tr>
						<c:forEach items="${ qList }" var="q">
							<tr>
								<td>${ q }</td>
								<td><textarea rows="2" cols="2" class="answerBox" name=""></textarea></td>
							</tr>
						</c:forEach>
					</table>
					<p>
						<input type="hidden" name="ans" value="check">
						<input type="submit" value="CHECK">
					</p>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>