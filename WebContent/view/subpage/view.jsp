<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container view">	
<h2>글상세보기</h2>
	<table class="table">
		<tr>
			<td class="bg-color1">번호</td>
			<td>
				${boardDTO.no}			
</td>
<td class="bg-color1">작성자</td>
<td>
	${boardDTO.clientId}			
	</td>
</tr>
<tr>
	<td class="bg-color1">작성일</td>
	<td>
		${boardDTO.postdate}			
</td>
<td class="bg-color1">조회수</td>
<td>
	${boardDTO.visitcount}			
	</td>
</tr>
<tr>
	<td class="bg-color1">제목</td>
	<td colspan="3">
		${boardDTO.title}
	</td>
</tr>
<tr>
	<td class="bg-color1">내용</td>
	<td colspan="3" style="height:450px;">
		${boardDTO.content}
	</td>
</tr>
<tr>
	<td class="bg-color1">첨부파일</td>
	<td colspan="3">
	<c:if test="${not empty boardDTO.ofile }">
		${boardDTO.ofile} 
		<a href="/MVC_Board/download?ofile=${boardDTO.ofile}&sfile=${boardDTO.sfile}&no=${boardDTO.no}">[다운로드]</a>
	</c:if>	
	</td>
</tr>			
<tr>
	<td colspan="4" align="center">
		<c:choose>
		<c:when test="${boardDTO.clientId == sessionScope.clientDTO.clientId }">
			<a href="/MVC_Board/pass?mode=edit&no=${param.no}" class="btn btn-dark">수정하기</a>
			<a href="/MVC_Board/pass?mode=delete&no=${param.no}" class="btn btn-dark">삭제하기</a>
		</c:when>
		</c:choose>							
		<a href="<%=request.getContextPath() %>/main?" class="btn btn-dark">목록보기</a>
	</td>
</tr>
	</table>

</div>
