<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ログイン済みかを判定・ログイン済みであればマイページへ飛ぶ
	if( session.getAttribute("ログイン") != null ){
		// 指定のページに飛ぶ
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}

	request.setAttribute("setLink", "/jApp/enquete/login.jsp");
	request.setAttribute("setLinkState", "トップページへ");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント作成</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				アカウント作成
				<div class="caution">
					<table>
						<tr>
							<td style="width: 120px;">&lt; ユーザーID &gt;</td>
							<td>半角英数字推奨<br>IDの重複は不可</td>
						</tr>
						<tr>
							<td>&lt; パスワード &gt;</td>
							<td>6文字以上推奨<br>（大文字小文字の区別なし）</td>
						</tr>
						<tr>
							<td>&lt; メールアドレス &gt;</td>
							<td>利用可能なものを使ってください</td>
						</tr>
						<tr>
							<td>&lt; 誕生日 &gt;</td>
							<td>半角数字のみで入力してください。<br>（例：1999年1月1日 → 19990101）</td>
						</tr>
					</table>
				</div>
				<!-- 未入力項目があった時のエラーメッセージ -->
				<c:if test="${ userName eq null }" >${ message }</c:if>
				<form action="/jApp/UserOperate" method="post">
					<table>
						<tr>
							<th colspan="2">アカウント情報入力</th>
						</tr>
						<tr>
							<td>ユーザーID</td>
							<td><input type="text" name="userName"></td>
						</tr>
						<tr>
							<td>パスワード</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>メールアドレス</td>
							<td><input type="text" name="mail"></td>
						</tr>
						<tr>
							<td>誕生日</td>
							<td><input type="text" name="birth"></td>
						</tr>
					</table>
					<p>
						<input type="submit" value="作成" class="submit">
					</p>
				</form>
				<% request.setAttribute("imageLink", "sns.png"); %>
				<jsp:include page="imageLink.jsp"></jsp:include>
			</div>
			<jsp:include page="footerLink.jsp"></jsp:include>
		</div>
	</body>
</html>