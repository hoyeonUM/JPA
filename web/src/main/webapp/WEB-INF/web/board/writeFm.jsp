<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ui" uri="/WEB-INF/tld/pagination.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function updateFn(){
	document.fm._method.value = "PUT"
	document.fm.submit();
}
function deleteFn(){
	document.fm._method.value = "DELETE"
	document.fm.submit();
}
function insertFn(){
	document.fm._method.value = "POST"
	document.fm.action="/board/new";
	document.fm.submit();
}
</script>
</head>
<body>
<form action="/board/${result.id }/edit" method="post" name="fm">
<input type="text" name="_method"/>
<table>
	<tr>
		<td>제목<input  type="text" name="title" value="${result.title }"/></td>
	</tr>
	<tr>
		<td>내용<input  type="text" name="contents" value="${result.contents }"/></td>
	</tr>
	<c:if test="${!empty result }">
	<tr>
		<td>등록일 : ${result.regDate }</td>
	</tr>
	<tr>
		<td>사용여부 : ${result.useYn }</td>
	</tr>
	<tr>
		<td>조회수 : ${result.readCount }</td>
	</tr>
	</c:if>
</table>
<c:if test="${!empty result.id }"> 
	<a href="#" onclick="updateFn();">수정</a>
	<a href="#" onclick="deleteFn();">삭제</a>
</c:if>
<c:if test="${empty result.id }">
	<a href="#" onclick="insertFn();">등록</a>
</c:if>

</form>
</body>
</html>