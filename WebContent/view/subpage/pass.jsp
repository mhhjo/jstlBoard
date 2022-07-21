<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${sessionScope.clientDTO != null }">
	<script type="text/javascript">
	function validateForm(form){
		if(form.clientPassword.value ==""){
			if(form.clientPassword.value == ""){
				alert("비밀번호를 입력해 주세요!");
				form.clientPassword.focus();
				return false;
			}
		}
	}
	</script>
	<div class="container register_wrap">
		<div>
	        <div class="form_wrap">
	        <c:choose>
		        <c:when test="${param.mode eq 'edit' }">
			        <h2>
			        	게시글 수정
			        </h2>
		        </c:when>
		        <c:when test="${param.mode eq 'delete' }">
			        <h2>
			        	게시글 삭제
			        </h2>
		        </c:when>
	        </c:choose>
	        	<div class="panel panel-default">
	        		<h3 class="panel-title">비밀번호를 입력해 주세요.</h3>
	        		<form role="form" method="post" action="pass" onsubmit="return validateForm(this)">
		        		<input type="hidden" name="no" value="${ param.no }" />
		        		<input type="hidden" name="mode" value="${ param.mode }" />      		
		    			<div class="form-group">
		    				<input type="password" name="clientPassword" class="form-control input-sm" placeholder="비밀번호">
		    			</div>
					    			
		    			<button type="submit" class="btn btn-dark btn-block">확인</button>		    		
		    		</form>
		    	</div>
	   		</div>
   		</div>
    	</div>
	</div>
</c:when>
<c:otherwise>
	<script>
   		alert("로그인이 필요한 서비스입니다!");
   		location.href="<%=request.getContextPath() %>/?subpage=login";
   	</script>
</c:otherwise>
</c:choose>