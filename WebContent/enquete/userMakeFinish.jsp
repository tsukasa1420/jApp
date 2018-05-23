<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	// アカウント作成完了の判定を受け取れなければログインページ（マイページに飛ぶ）
	if( request.getAttribute("userMakeFinish") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント作成完了</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				accountMakeFinish
				<table style="margin: 0 auto;">
					<tr>
						<td>user</td>
						<td><%= request.getAttribute("userName") %></td>
					</tr>
					<tr>
						<td>pass</td>
						<td><%= request.getAttribute("password") %></td>
					</tr>
					<tr>
						<td>mail</td>
						<td><%= request.getAttribute("mail") %></td>
					</tr>
					<tr>
						<td>birthday</td>
						<td><%= request.getAttribute("birth") %></td>
					</tr>
				</table>
				<p style="text-align: right;">
					<a href="/jApp/enquete/myPage.jsp">マイページへ</a>
				</p>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>