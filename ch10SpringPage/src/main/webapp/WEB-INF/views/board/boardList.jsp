<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-main-style">
	<h2>게시판 목록</h2>
	<!-- 검색 기능 -->
	<form id="search_form" action="list.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="id">아이디</option>
				</select>
			</li>
			<li>
				<input type="search" size="16" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
			</li>
		</ul>
	</form>
	<div class="align-right">
		<c:if test="${!empty user_id}">
			<input type="button" value="글쓰기" onclick="location.href='write.do'">
		</c:if>
	</div>
	<c:if test="${count == 0 }">
		<div class="result-display">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0 }">
		<div>
			<table>
			<tr>
				<th>번호</th>
				<th width="300">제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="board" items="${list }">
			<tr>
				<td>${board.num }</td>
				<td><a href="detail.do?num=${board.num}">${board.title }</a></td>
				<td>${board.id }</td>
				<td>${board.reg_date }</td>
				<td>${board.hit }</td>
			</tr>
			</c:forEach>
			</table>
			<div class="align-center">${pagingHtml }</div>
		</div>
	</c:if>
</div>