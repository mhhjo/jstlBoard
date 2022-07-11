<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${sessionScope.clientDTO != null }">
		<div class="container login_wrap">
	       <div>
	       <div class="form_wrap">
		       <h2>
		       	회원탈퇴
		       </h2>
		       	<div class="panel panel-default">
		       		<div class="panel-heading">
				    		<h3 class="panel-title">비밀번호를 입력하세요</h3>
				 			</div>
				 			<div class="panel-body">
				    		<form role="form" method="post" action="delete">
				    		<!-- <input type="hidden" name="command" value="delete" > -->
				    			<div class="form-group">
				    				<input type="text" name="clientId" class="form-control input-sm" 
				    				value="${sessionScope.clientDTO.clientId }" readonly="readonly">
				    			</div>
		
				    			<div class="form-group">
				    				<input type="password" name="clientPassword" class="form-control input-sm" placeholder="비밀번호">
				    			</div>
				    			<input type="submit" value="회원탈퇴" class="btn btn-dark btn-block">
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