<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ui" uri="/WEB-INF/tld/pagination.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/resource/js/common.js"/></script>
</head>
<body>
<form action="" method="get" name="fm">
	<table>
	<c:forEach items="${list }" var="item">
		<tr>
			<td>${item.id }</td>
			<td><a href="/board/${item.id}/edit">${item.title }</a></td>
		</tr> 
	</c:forEach>
	</table>
	<ui:pagination paginationInfo="${paginationInfo}" type="userPaging" jsFunction="list_page" action="/boards" />
	<br/><a href="/board/new">등록</a>
	<br/>
	<select name="searchType">
		<option value="" <c:if test="${empty params.searchType}">selected="selected"</c:if>>전체</option>
		<option value="boardTitle" <c:if test="${params.searchType eq 'boardTitle'}">selected="selected"</c:if>>제목</option>
		<option value="boardContents" <c:if test="${params.searchType eq 'boardContents'}">selected="selected"</c:if>>내용</option>
	</select>
	<input name="searchValue" value="${params.searchValue }"/><button onclick="goSearch('/boards')" type="submit">검색</button>
	<input type="hidden" name="page" value="${paginationInfo.currentPageNo}" />
	<input type="hidden" name="recordCountPerPage" value="${paginationInfo.recordCountPerPage}" />
	<input type="hidden" name="pageSize" value="${paginationInfo.pageSize}" /> 
</form>
</body>
</html>