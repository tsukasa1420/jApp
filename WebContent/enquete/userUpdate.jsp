<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// 直リンク禁止
	if( request.getAttribute("toUserUpdate") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html>
<!--
	インプット
	-> useInfoページでのパラメータ（name=""で渡すデータ）
	-> パラメータによって表示が異なるようにする
	アウトプット
	-> 入力された値
	-> 何の変更をしたかの判定にhiddenでvalue=.".$.{.}."を使う
	-> javaに飛ばしてUPDATE・DELETE命令を行う
-->
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザー情報変更</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				ユーザー情報変更
				<form action="/jApp/UserEdit" method="post">
					<table>
						<tr>
							<th colspan="3">質問</th>
						</tr>
						<tr>
							<!-- ユーザー名を編集する場合 -->
							<c:if test="${ edit eq 'id' }" >
								<td>ユーザーID</td>
								<td><input type="text" name="newData"></td>
								<input type="hidden" name="Edit" value="UPDATE">
							</c:if>


							<!-- パスワードを編集する場合 -->
							<c:if test="${ edit eq 'pass' }" >
								<td>パスワード</td>
								<td><input type="password" name="newData"></td>
								<input type="hidden" name="Edit" value="UPDATE">
							</c:if>


							<!-- メールアドレスを編集する場合 -->
							<c:if test="${ edit eq 'mail' }" >
								<td>メールアドレス</td>
								<td><input type="text" name="newData"></td>
								<input type="hidden" name="Edit" value="UPDATE">
							</c:if>
							<!-- 誕生日を編集する場合 -->
							<c:if test="${ edit eq 'birthday' }" >
								<td>誕生日</td>
								<td><input type="text" name="newData"></td>
								<input type="hidden" name="Edit" value="UPDATE">
							</c:if>


							<!-- ユーザー情報を削除する場合 -->
							<c:if test="${ edit eq 'del' }" >
								<td>アカウント削除</td>
								<input type="hidden" name="Edit" value="DELETE">
							</c:if>
							<td>
								<input type="submit" value="実行" class="submit userUpdateButton">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>