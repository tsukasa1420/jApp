<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	// 直リンク禁止
	if( request.getAttribute("toUserUpdateCheck") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html>
<!--
	画面遷移
	userInfo -> userUpdateCheck -> userUpdate
	*
	インプット
	-> ナシ
	アウトプット
	-> ユーザー名・パスワードがログインユーザーの物であれば遷移
	-> 一致しなければuseInfoに遷移
-->
<html>
	<head>
		<meta charset="UTF-8">
		<title>本人確認</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				本人確認
				<form action="/jApp/UserEdit" method="post">
					<table style="margin: 0 auto;">
						<tr>
							<td>USER ID</td>
							<td><input type="text" name="userID"></td>
						</tr>
						<tr>
							<td>PASSWORD</td>
							<td><input type="password" name="password"></td>
						</tr>
					</table>
					<p>
						<input type="hidden" name="Edit" value="checkUser">
						<input type="submit" value="CHECK">
					</p>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>