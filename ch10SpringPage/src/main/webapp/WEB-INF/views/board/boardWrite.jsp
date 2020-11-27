<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main-style">
	<h2>글쓰기</h2>
	<form:form action="write.do" enctype="multipart/form-data" commandName="boardVO">
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title"/><form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content"/><form:errors path="content" cssClass="error-color"/>
			</li>
			<li>
				<label for="title">파일업로드</label>
				<input type="file" name="upload" id="upload"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form:form>

</div>