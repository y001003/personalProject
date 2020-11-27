<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h2>회원정보수정</h2>
	<form:form action="update.do" commandName="memberVO">
		<form:hidden path="mem_num"/>
		<ul>
			<li>
				<label for="name">이름</label>
				<form:input path="name"/><form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="phone">전화번호</label>
				<form:input path="phone"/><form:errors path="phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label>
				<form:input path="email"/><form:errors path="email" cssClass="error-color"/>
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<form:input path="zipcode"/><form:errors path="zipcode" cssClass="error-color"/>
			</li>
			<li>
				<label for="address1">주소</label>
				<form:input path="address1"/><form:errors path="address1" cssClass="error-color"/>
			</li>
			<li>
				<label for="address2">상세주소</label>
				<form:input path="address2"/><form:errors path="address2" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath }/main/main.do'">
		</div>
	</form:form>
</div>