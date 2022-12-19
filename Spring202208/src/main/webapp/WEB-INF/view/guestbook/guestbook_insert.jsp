<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook/guestbook_insert</title>
<link rel='stylesheet' href='../css/guestbook.css'>

</head>
<body>
<form name='frm_guestbook_insert' class='frm_guestbook_insert' method='post'>
	<label>작성자</label><input type="text" name="id" class="id" value="" size="8"/>
	<label>작성일</label>
	<output>
		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>
	</output>
	
	<br/>
	<textarea rows="10" cols="50" name='doc'></textarea>
	<br/>
	<label>암호</label>
	<input type="password" name="pwd" class="pwd"/>
	<br/>
	<input type='button' value='작성' class='btnGuestbookSave'>
</form>
</body>
</html>