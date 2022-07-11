<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${sessionScope.clientDTO != null }">
	<div class="container">	
	<h2>글쓰기</h2>
	<form method="post" action="write" enctype="multipart/form-data" onsubmit="return validateForm(this);">
		<table class="table" style="width:90%">
			<tr>
				<td class="bg-color1">작성자</td>
				<td>
					<input type="text" name="clientid" class="form-control" style="width:150px;" value="${clientDTO.clientId}" readonly>					
				</td>
			</tr>
			<tr>
				<td class="bg-color1">제목</td>
				<td>
					<input type="text" name="title" class="form-control" style="width:90%;">					
				</td>
			</tr>
			<tr>
				<td class="bg-color1">내용</td>
				<td>
					<textarea name="content" class="form-control" rows="15"
					style="width:90%; height:200px; resize:none;"></textarea>
				</td>
			</tr>
			<tr>
				<td class="bg-color1">첨부파일</td>
				<td>
					<input type="file" name="ofile" class="form-control">		
				</td>
			</tr>
			<!-- <tr>
				<td class="bg-color1">비밀번호</td>
				<td>
					<input type="password" name="pass" class="form-control" style="width:100px;">		
				</td>
			</tr> -->
			<tr>
				<td colspan="2" align="center">
					<button class="btn bg-dark">작성완료</button>
					<button type="reset" class="btn bg-dark">다시입력</button>
					<!-- <button type="button" class="btn bg-color2" onclick="location.href='List.jsp'">목록보기</button> -->
					<a href="<%=request.getContextPath() %>/main?" class="btn bg-dark">목록보기</a>
				</td>
			</tr>
		</table>
	</form>

</div>
</c:when>
<c:otherwise>
	<script>
   		alert("로그인이 필요한 서비스입니다!");
   		location.href="<%=request.getContextPath() %>/?subpage=login";
   	</script>
</c:otherwise>
</c:choose>