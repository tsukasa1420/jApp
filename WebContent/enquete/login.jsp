<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ログイン済みかを判定・ログイン済みであればマイページへ飛ぶ
	if( session.getAttribute("ログイン") != null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}

	request.setAttribute("setLink", "/jApp/enquete/userMake.jsp");
	request.setAttribute("setLinkState", "アカウント作成");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				トップページ
				<!-- ログインしてない時のエラーメッセージ -->
				<c:if test="${ ログイン eq null }" >${ message }</c:if>
				<form action="/jApp/MyPage" method="post">
					<table>
						<tr>
							<th colspan="2">ログイン情報入力</th>
						</tr>
						<tr>
							<td>ユーザーID</td>
							<td><input type="text" name="userName"></td>
						</tr>
						<tr>
							<td>パスワード</td>
							<td><input type="password" name="password"></td>
						</tr>
					</table>
					<p>
						<input type="hidden" name="log" value="in">
						<input type="submit" value="ログイン" class="submit">
					</p>
				</form>
				<% request.setAttribute("imageLink", "login.png"); %>
				<jsp:include page="imageLink.jsp"></jsp:include>
			</div>
			<jsp:include page="footerLink.jsp"></jsp:include>
		</div>
	</body>
</html>