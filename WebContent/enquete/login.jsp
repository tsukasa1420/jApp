<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
		<link rel="stylesheet" href="style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				login
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
						<input type="submit" value="ログイン">
					</p>
				</form>
			</div>
			<br>
			<div class="containtsBox">
				accountMake
				<a href="userMake.jsp">
					<div class="linkBox">
						アカウント作成
					</div>
				</a>
			</div>
			<br>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>