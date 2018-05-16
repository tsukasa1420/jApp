<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// be usable session
	HttpSession loginSession = request.getSession(false);

	// judge LOGIN or LOGOUT ( areba )
	if( loginSession.getAttribute("ログイン") != null ){
		// jump jsp. and prepare.
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}
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
				login
				<!-- ログインしてない時のエラーメッセージ -->
				<c:if test="${ ログイン eq null }" >${ message }</c:if>
				<form action="/jApp/MyPage" method="post">
					<table style="margin:  0 auto;">
						<tr>
							<td>user</td>
							<td><input type="text" name="userName"></td>
						</tr>
						<tr>
							<td>pass</td>
							<td><input type="password" name="password"></td>
						</tr>
					</table>
					<p>
						<input type="hidden" name="log" value="in">
						<input type="submit" value="ログイン">
					</p>
				</form>
			</div>
			<div class="containtsBox">
				accountMake
				<a href="/jApp/enquete/userMake.jsp">
					<div class="linkBox">
						アカウント作成
					</div>
				</a>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>