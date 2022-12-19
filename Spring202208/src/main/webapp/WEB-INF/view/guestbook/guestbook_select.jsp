<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='css/guestbook.css'>
<script defer src='js/guestbook.js'></script>
<title>guestbook/guestbook_select.jsp</title>

</head>
<body>
<div id='guestbook_list'>
	<%@include file="guestbook_insert.jsp" %>
	
		<form name='frm_gb_search' class='frm_gb_search' method='post'>
			
			<input type='text' name='findStr' class='frm_gb_search' value='${pVo.findStr }'/>
			<input type='button' value='검색' class='btnSearch'/>
			<input type='text' name='nowPage' value='${pVo.nowPage }'/> 
		</form>
		
		<div class='guestbook_btn'>
			<c:if test="${pVo.startNo>1 }">
				<input type='button' value='&lt;' class='btnPrev'>
			</c:if>
			<c:if test="${pVo.totSize>pVo.endNo }">
				<input type='button' value='&gt;' class='btnNext'>
			</c:if>
		</div>
		
		<div class='guestbook_items'>
			<c:forEach var='vo' items="${list }">
				<div class='item'>
				<form  name='frm_guestbook' class='frm_guestbook' method='post'>
					<div class='btnZone'>
						<input type='button' class='btnUpdate' value='수정' 
									onclick='gb.modifyView(this.form)'/>
						<input type='button' class='btnDelete' value='X' onclick='gb.modalView(this.form)'/>
					</div>
					<label>작성자</label><input type="text" name="id" class="id" value="${vo.id }" size="8"/>
					<label>작성일</label><input type="text" name="nal" class="nal" value="${vo.nal }" size="15"/>
					<br/>
					<textarea rows="10" cols="50" name='doc'>${vo.doc }</textarea>
					<br/>
					<div class='UpdateZone'>
					<label>암호</label>
					<input type='password' name='pwd' />
					<input type="button" value="수정" 
									onclick='gb.update(this.form)'/>
					<input type="button" value="취소" class='btnCancel'
									onclick='gb.cancel(this.form)' />
					</div>
					
					<input type='hidden' name='sno' value='${vo.sno}'/>
				</form>
				</div>
			</c:forEach>
		</div>
		
		<div class='guestbook_btn'>
			<c:if test="${pVo.startNo>1 }">
				<input type='button' value='&lt;' class='btnPrev'>
			</c:if>
			<c:if test="${pVo.totSize>pVo.endNo }">
				<input type='button' value='&gt;' class='btnNext'>
			</c:if>
		</div>
		
</div>
 
 <div id='modal'>
 	<div id='content'>
 		<input type='button' id='btnClose' value='X'/>
 		<span>암호를 입력하세요</span>
 		<input type='password' id='pwd'/>
 		<input type='button' value='확인' id='btnCheck'/>
 	</div>
 	
 </div>
</body>
</html>