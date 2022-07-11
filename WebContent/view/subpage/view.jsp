<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">	
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
	<td class="bg-color1" style="width:30%;">첨부파일</td>
	<td style="width:25%;">
	<c:if test="${not empty dto.ofile }">
${boardDTO.ofile} 
<a href="/download?ofile=${boardDTO.ofile}&sfile=${boardDTO.sfile}&no=${boardDTO.no}">[다운로드]</a>
</c:if>	
	</td>			
</tr>			
<tr>
	<td colspan="4" align="center">
		<a href="pass?mode=edit&idx=${param.no}" class="btn bg-color2">수정하기</a>
		<a href="pass?mode=delete&idx=${param.no}" class="btn bg-color2">삭제하기</a>							
				<form method="get" action="main">
           			<input class="nav-link nav_btn" type="submit" value="목록보기">
               	</form>
	</td>
			</tr>
		</table>

</div>
