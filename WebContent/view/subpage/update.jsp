<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
<c:when test="${sessionScope.clientDTO != null }">
<div class="container register_wrap">
        <div>
        <div class="form_wrap">
        <h2>
        	회원정보 수정
        </h2>
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">수정사항을 입력해주세요.</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" method="post" action="update">
			    		<!-- <input type="hidden" name="command" value="update" > -->
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="clientId" class="form-control input-sm" 
			                value="${clientDTOinfo.clientId }" readonly>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="clientName" class="form-control input-sm" 
			    						value="${clientDTOinfo.clientName}">
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<input type="email" name="clientEmail" class="form-control input-sm" 
			    				value="${clientDTOinfo.clientEmail}">
			    			</div>

			    			<div class="form-group">
			    				<input type="password" name="clientPassword" class="form-control input-sm" placeholder="비밀번호">
			    			</div>
			    			
			    			<input type="submit" value="수정" class="btn btn-dark btn-block">			    		
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
