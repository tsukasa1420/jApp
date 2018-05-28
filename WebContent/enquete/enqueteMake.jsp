<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ログイン済みかを判定・未ログインであればログインページへ飛ぶ
	if( session.getAttribute("ログイン") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アンケート作成</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				アンケート作成<br>
				<!-- 入力値が無い、DBエラーなどの時のエラーメッセージ -->
				<c:if test="${ message ne null }" >${ message }</c:if>
				<form action="/jApp/EnqueteOperate">
					<table>
						<tr>
							<th colspan="2">質問</th>
						</tr>
						<tr>
							<td>アンケートID<br>（半角英字 4文字推奨）</td>
							<td><input type="text" name="enqID"></td>
						</tr>
						<tr>
							<td>アンケート名</td>
							<td><input type="text" name="enqName"></td>
						</tr>
						<c:forEach var="index" begin="1" end="5">
							<tr>
								<td  style="text-align: center;">Q${ index }</td>
								<td><textarea rows="2" cols="2" name="q${ index }"></textarea></td>
							</tr>
						</c:forEach>
					</table>
					<p>
						<input type="hidden" name="enq" value="make">
						<input type="submit" value="作成" class="submit">
					</p>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>