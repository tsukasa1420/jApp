<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// アンケート回答ページ以外のリンクからこのページに来た場合はマイページに飛ぶ
//	if( request.getAttribute("checkEnquete") == null ){
//		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
//		rd.forward(request, response);
//	}

	int index = 0;
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				<table>
					<tr>
						<th colspan="2">質問</th><th style="min-width: 150px;">回答</th>
					</tr>
					<c:forEach items="${ res_qList }" var="q">
						<%
							index++;
							String ans = "res_aList" + index;
						%>
						<tr>
							<td>Q<%= index %></td>
							<td>${ q }</td>
							<td><%= session.getAttribute(ans) %></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>