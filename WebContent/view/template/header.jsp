<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron text-center main_banner">
	<div class="inner_box">
  		<h1 style="color:white;">JSTL 게시판</h1>
  		<p style="color:white;">JSTL을 사용하여 게시판 및 회원 관리를 구현해보았습니다!</p>
  	</div>
</div>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a href="#" class="navbar-brand">JSTL 게시판</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar7">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-collapse collapse justify-content-stretch" id="navbar7">
        <ul class="navbar-nav ml-auto">
        	<li class="nav-item">
        		<a class="nav-link" href="<%=request.getContextPath()%>/?subpage=home">Home</a>
        	</li>
            <li class="nav-item">
                <form method="get" action="main">
               			<input class="nav-link nav_btn" type="submit" value="List">
               	</form>
            </li>
            <li class="nav-item">
            	<c:choose>
            		<c:when test="${sessionScope.clientDTO == null }">
                		<a class="nav-link" href="<%=request.getContextPath() %>/?subpage=register">Sign Up</a>
               		</c:when>
               		<c:otherwise>
               			<a class="nav-link" href="<%=request.getContextPath() %>/?subpage=account_edit">Edit Account</a>
               		</c:otherwise>
               	</c:choose>
            </li>
            <li class="nav-item">
            	<c:choose>
	            	<c:when test="${sessionScope.clientDTO == null }">
	                	<a class="nav-link" href="<%=request.getContextPath() %>/?subpage=login">Login</a>
	                </c:when>
	                <c:otherwise>
	                	<form method="post" action="logout">
	                		<input class="nav-link nav_btn" type="submit" value="Logout">
	                	</form>
	                </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</nav>