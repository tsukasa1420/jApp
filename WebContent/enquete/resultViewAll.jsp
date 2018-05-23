<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/jApp/enquete/style/whole.css">
	</head>
	<body>
		<div id="container">
			<div class="containtsBox">
				アンケート回答結果一覧
				<ul>
					<c:forEach items="${resultList}" var="result">
						<li><a href="/jApp/ResultOperate?priNo=${ result.getPriNo() }">${ result.getEnqID() }</a>
					</c:forEach>
				</ul>
			</div>
			<jsp:include page="footLink.html"></jsp:include>
		</div>
	</body>
</html>