<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 게시판</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
<script src="js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="header">
		<c:import url="/view/template/header.jsp"></c:import>
	</div>
	<div class="content">
		<%
			String subpage = "mainPage";
			if(request.getParameter("subpage") != null){
				subpage = request.getParameter("subpage");
			}
			subpage = "view/subpage/"+subpage+".jsp";
		%>
		<jsp:include page="<%=subpage %>"></jsp:include>
	</div>
	<div class="footer jumbotron text-center">
		<c:import url="/view/template/footer.jsp"></c:import>
	</div>
</body>
</html>