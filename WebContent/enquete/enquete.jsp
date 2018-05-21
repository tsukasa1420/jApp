<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int qNum = 0;
	String qAnswer = "qAnswer";
	request.setAttribute("value", qAnswer);
%>
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
				<form action="/jApp/EnqueteOperate" method="post">
					<table>
						<tr>
							<th colspan="2">質問</th><th style="min-width: 150px;">回答</th>
						</tr>
						<c:forEach items="${ qList }" var="q">
							<tr>
								<%
									qNum++;
									request.setAttribute("value", qAnswer + qNum);
								%>
								<td>Q<%=qNum %></td>
								<td>${ q }</td>
								<td><textarea rows="2" cols="2" class="answerBox" name="${ value }"></textarea></td>
							</tr>
						</c:forEach>
						<%
							session.setAttribute("qNum", qNum);
						%>
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