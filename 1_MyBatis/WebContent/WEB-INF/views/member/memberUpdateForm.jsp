<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	#infoTable{margin: auto;}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">내 정보 수정</h1>
	<form action="${ contextPath }/mupdate.me" method="post">
		<table id="infoTable">
			<tr>
				<td width="100px">* 아이디</td>
				<td>${member.userId}</td>
			</tr>
			<tr>
				<td>* 이름</td>
				<td><input type="text" name="userName" value="${member.userName}" required></td>
			</tr>
			<tr>
				<td>* 닉네임</td>
				<td><input type="text" name="nickName" value="${member.nickName}" required></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;이메일</td>
				<td><input type="email" name="email" value="${member.email}" ></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;생년월일</td>
				<td>
					<select name="year">
						<c:forEach begin="<%= new GregorianCalendar().get(Calendar.YEAR) - 100 %>" end="<%= new GregorianCalendar().get(Calendar.YEAR) %>" var="i">
							<c:if test="${fn:substring(member.birthDay, 0, 4) == i }">
								<option value="${ i }" selected>${ i }</option>
							</c:if>
							<c:if test="${fn:substring(member.birthDay, 0, 4) != i }">
								<option value="${ i }">${ i }</option>
							</c:if>							
						</c:forEach>
					</select>
					<select name="month">
						<c:forEach begin="1" end="12" var="i">
							<c:if test="${fn:substring(member.birthDay, 5, 7) == i }">
								<option value="${ i }" selected>${ i }</option>
							</c:if>
							<c:if test="${fn:substring(member.birthDay, 5, 7) != i }">
								<option value="${ i }">${ i }</option>
							</c:if>							
						</c:forEach>
					</select>
					<select name="date">
						<c:forEach begin="1" end="31" var="i">
							<c:if test="${fn:substring(member.birthDay, 8, 10) == i }">
								<option value="${ i }" selected>${ i }</option>
							</c:if>
							<c:if test="${fn:substring(member.birthDay, 8, 10) != i }">
								<option value="${ i }">${ i }</option>
							</c:if>
						</c:forEach>
					</select>					
				</td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;전화번호</td>
				<td><input type="text" name="phone" value="${member.phone}"></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;주소</td>
				<td><input type="text" name="address" value="${member.address}"></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;성별</td>
				<td>
					<input type="radio" name="gender" value="M" <c:if test="${member.gender == 'M'}">checked</c:if>> 남자 
					<input type="radio" name="gender" value="F" <c:if test="${member.gender == 'F'}">checked</c:if>> 여자 
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<br>
						<input type="submit" value="수정">
						<button type="reset">취소</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>