<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// マイページ以外のリンクからこのページに来た場合はマイページに飛ぶ
	if( request.getAttribute("answerEnquete") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}

	// 質問番号、質問パラメータ番号を用意して、${}で使えるようにする。
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
				アンケート
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
								<td><textarea rows="2" cols="2" name="${ value }"></textarea></td>
							</tr>
						</c:forEach>
						<%
							session.setAttribute("qNum", qNum);
						%>
					</table>
					<p>
						<input type="hidden" name="enq" value="check">
						<input type="submit" value="回答確認" class="submit">
					</p>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>