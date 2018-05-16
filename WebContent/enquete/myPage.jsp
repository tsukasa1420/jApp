<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	// be usable session
	HttpSession loginSession = request.getSession(false);

	// judge LOGIN or LOGOUT ( areba )
	if( loginSession.getAttribute("ログイン") == null ){
		// jump jsp. and prepare.
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MyPage</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				MyPage
				<p style="text-align: right;">
					<%= session.getAttribute("userName") %> さんがログイン中<br>
					<a href="/jApp/MyPage?log=out">ログアウト</a>
				</p>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>