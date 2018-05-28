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
				アカウント作成完了
				<table>
					<tr>
						<th colspan="2">ユーザー情報</th>
					</tr>
					<tr>
						<td>ユーザーID</td>
						<td><%= request.getAttribute("userName") %></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><%= request.getAttribute("password") %></td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><%= request.getAttribute("mail") %></td>
					</tr>
					<tr>
						<td>誕生日</td>
						<td><%= request.getAttribute("birth") %></td>
					</tr>
				</table>
				<% request.setAttribute("imageLink", "finish.png"); %>
				<jsp:include page="imageLink.jsp"></jsp:include>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>