<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-main-style">
	<h2>회원정보</h2>
	<ul>
		<li>이름 : ${member.name }</li>
		<li>전화번호 : ${member.phone }</li>
		<li>이메일 : ${member.email }</li>
		<li>우편번호 : ${member.zipcode }</li>
		<li>주소 : ${member.address1 }</li>
		<li>상세주소 : ${member.address2 }</li>
		<li>가입날짜 : ${member.reg_date }</li>
		<li>최근 정보수정 날짜 : ${member.modify_date }</li>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="회원정보 수정" onclick="location.href='update.do'">
		<input type="button" value="비밀번호 변경" onclick="location.href='changePassword.do'">
		<input type="button" value="회원탈퇴" onclick="location.href='delete.do'">
		<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</p>
</div>