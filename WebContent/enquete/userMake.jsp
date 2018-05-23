<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ログイン済みかを判定・ログイン済みであればマイページへ飛ぶ
	if( session.getAttribute("ログイン") != null ){
		// jump jsp. and prepare.
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}
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
				accountMake
				<!-- 未入力項目があった時のエラーメッセージ -->
				<c:if test="${ userName eq null }" >${ message }</c:if>
				<form action="/jApp/UserOperate" method="post">
					<table style="margin:  0 auto;">
						<tr>
							<td>user</td>
							<td><input type="text" name="userName"></td>
						</tr>
						<tr>
							<td>pass</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>mail</td>
							<td><input type="text" name="mail"></td>
						</tr>
						<tr>
							<td>birthday</td>
							<td><input type="text" name="birth"></td>
						</tr>
					</table>
					<p>
						<input type="submit" value="accountMake">
					</p>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>