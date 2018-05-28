<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// アンケート回答ページ以外のリンクからこのページに来た場合はマイページに飛ぶ
	if( request.getAttribute("checkEnquete") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}

	int qNum = 0;
%>
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
				回答確認
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
						<input type="hidden" name="enq" value="send">
						<input type="submit" value="アンケート結果送信" class="submit">
					</p>
				</form>
			</div>
			<br>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>