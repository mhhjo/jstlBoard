<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${sessionScope.clientDTO != null }">		
		<div class="container account_wrap">
	       <div>
	       <div class="form_wrap">
		       <h2>
		       	계정 관리
		       </h2>
		       	<div class="panel panel-default">
		       		<div class="panel-heading">
				 			</div>
				 			<div class="panel-body">
				 			<form method="post" action="updatePage">
					    		<input type="submit" class="btn btn-dark btn-square-md" value="회원정보 수정">
				    		</form>
					    		<a href="<%=request.getContextPath() %>/?subpage=delete" class="btn btn-dark btn-square-md">
					    			회원탈퇴
					    		</a>>				    		
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