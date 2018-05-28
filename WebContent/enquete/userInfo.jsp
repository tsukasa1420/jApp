<%@page import="enquete.Java.BeanUser"%>
<%@ page import="enquete.Java.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ログイン済みかを判定・未ログインであればログインページへ飛ぶ
	if( session.getAttribute("ログイン") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
		rd.forward(request, response);
	}

	// ユーザ情報表示の為
	User user = new User();
	BeanUser beanU = user.userInfoFunc(request);
%>
<!DOCTYPE html>
<!--
	ユーザーID、パスワード、メール、誕生日の表示とそれぞれの変更ボタン
	アカウント削除ボタン
-->
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザー情報ページ</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				ユーザー情報
				<!-- ユーザー名かパスワードが間違っていた場合 -->
				<c:if test="${ message ne null }" >${ message }</c:if>
				<table>
					<tr>
						<th colspan="3">ユーザー情報</th>
					</tr>
					<tr>
						<td>ユーザーID</td>
						<td><%= beanU.getUserName() %></td>
						<td>
							<form action="/jApp/UserEdit" method="post">
								<input type="hidden" name="Edit" value="id">
								<input type="submit" value="変更" class="submit">
							</form>
						</td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><%= beanU.getPassword() %></td>
						<td>
							<form action="/jApp/UserEdit" method="post">
								<input type="hidden" name="Edit" value="pass">
								<input type="submit" value="変更" class="submit">
							</form>
						</td>
					</tr>
					<tr>
						<td>メールアドレス</td>
						<td><%= beanU.getMail() %></td>
						<td>
							<form action="/jApp/UserEdit" method="post">
								<input type="hidden" name="Edit" value="mail">
								<input type="submit" value="変更" class="submit">
							</form>
						</td>
					</tr>
					<tr>
						<td>誕生日</td>
						<td><%= beanU.getBirth() %></td>
						<td>
							<form action="/jApp/UserEdit" method="post">
								<input type="hidden" name="Edit" value="birthday">
								<input type="submit" value="変更" class="submit">
							</form>
						</td>
					</tr>
				</table>
				<form action="/jApp/UserEdit" method="post">
					<p style="text-align: right;">
						<input type="hidden" name="Edit" value="del">
						<input type="submit" value="アカウント削除" class="submit attention">
					</p>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>