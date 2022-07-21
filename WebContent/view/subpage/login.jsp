<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${sessionScope.clientDTO == null }">
		<div class="container login_wrap">
	       <div>
	       <div class="form_wrap">
		       <h2>
		       	JSTL 게시판 로그인
		       </h2>
		       	<div class="panel panel-default">
		       		<div class="panel-heading">
				    		<h3 class="panel-title">필수정보를 입력해주세요. <small>환영합니다!</small></h3>
				 			</div>
				 			<div class="panel-body">
				    		<form role="form" method="post" action="login">
				    		<!-- <input type="hidden" name="command" value="login" > -->
				    			<div class="form-group">
				    				<input type="text" name="clientId" class="form-control input-sm" placeholder="아이디">
				    			</div>
		
				    			<div class="form-group">
				    				<input type="password" name="clientPassword" class="form-control input-sm" placeholder="비밀번호">
				    			</div>
				    			
				    			<input type="submit" value="로그인" class="btn btn-dark btn-block">
				    			<div class="text-center login_sub">
				    				<a class="underline" href="<%=request.getContextPath() %>/?subpage=register">
				    					회원가입
				    				</a>
				    			</div>			    		
				    		</form>
				    	</div>
		    		</div>
		   		</div>
		   	</div>
	    </div>
    </c:when>
    <c:otherwise>
    	<script>
    		alert("잘못된 접근입니다!");
    		location.href="http://localhost:8081/MVC_Board/";
    	</script>
    </c:otherwise>
</c:choose>