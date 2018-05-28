<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	// アンケート回答ページ以外のリンクからこのページに来た場合はマイページに飛ぶ
	if( request.getAttribute("finishEnqueteMake") == null ){
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>アンケート終了</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				<div class="finishState">
					アンケートを作成しました。<br>
					お疲れ様でした。
				</div>
				<% request.setAttribute("imageLink", "finish.png"); %>
				<jsp:include page="imageLink.jsp"></jsp:include>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>