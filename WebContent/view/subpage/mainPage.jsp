<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
	<h2>목록보기</h2>
	<form method="get" class="search" action="main">
		<div style="text-align:center;">
			<select name="searchField" class="custom-select" style="width:25%; display:inline-block;">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="searchWord" class="form-control" style="width:40%;display:inline-block;vertical-align:bottom !important;"/>
			<input type="submit" value="검색하기" class="btn btn-dark" />
		</div>
	</form>
	
	<table class="table">
		<tr class="bg-color1" align="center">
			<th width="10%">번호</th>
			<th width="*">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
			<th width="8%">첨부</th>
		</tr>
	<c:choose>
		<c:when test="${empty boardLists}">
			<tr>
				<td colspan="6" align="center">
				등록된 게시물이 없습니다.
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${boardLists}" var="row" varStatus="loop">
			<tr align="center">
				<td style="vertical-align:middle;">
					${map.totalCount-(((map.pageNum-1)*map.pageSize)+loop.index)}
				</td>
				<td style="vertical-align:middle;">
					<a href="view?no=${row.no}">${row.title}</a>
				</td>
				<td style="vertical-align:middle;">${row.clientId}</td>
				<td style="vertical-align:middle;">${row.visitcount}</td>
				<td style="vertical-align:middle;">${row.postdate}</td>
				<td>
					<c:if test="${not empty row.ofile }">
						<a class="down_icon" href="/MVC_Board/download?ofile=${ row.ofile }&sfile=${ row.sfile }&no=${ row.no }">
							<c:set var="filename" value="${ row.ofile }" />
							<c:set var="fileNm" value="${ fn:toLowerCase(filename) }" />
							<c:forTokens var="token" items="${ fileNm }" delims="." varStatus="status" >
								<c:if test="${ status.last }">
									<c:choose>
										<%-- html 파일 --%>
										<c:when test="${ token eq 'html' }">
											<img src="img/html_icon.png" alt="${ filename }" />
										</c:when>
										<%-- img 파일 --%>
										<c:when test="${ token eq 'jpg' || token eq 'jpeg' || token eq 'png' || token eq 'gif' || token eq 'bmp' }">
											<img src="img/img_icon.png" alt="${ filename }" />
										</c:when>
										<%-- pdf 파일 --%>
										<c:when test="${ token eq 'pdf' }">
											<img src="img/pdf_icon.png" alt="${ filename }" />
										</c:when>
										<%-- xml 파일 --%>
										<c:when test="${ token eq 'xml' }">
											<img src="img/xml_icon.png" alt="${ filename }" />
										</c:when>
										<%-- zip 파일 --%>
										<c:when test="${ token eq 'zip' }">
											<img src="img/zip_icon.png" alt="${ filename }" />
										</c:when>
									</c:choose>
								</c:if>
							</c:forTokens>
						</a>					
					</c:if>
				</td>
			</tr>	
			</c:forEach>
		</c:otherwise>
	</c:choose>		
	</table>
	
	<div style="text-align:right;">
		${map.pagingImg }
		<a class="btn btn-dark" href="<%=request.getContextPath() %>/?subpage=write">글쓰기</a>
	</div>
</div>