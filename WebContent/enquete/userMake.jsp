<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アカウント作成</title>
		<link rel="stylesheet" href="style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				accountMake
				<form action="/jApp/UserMake" method="post">
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
			<br>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>