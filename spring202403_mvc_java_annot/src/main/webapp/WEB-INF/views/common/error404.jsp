<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
<style>
  a{
      text-decoration: none;
      font: 13px Arial, sans-serif;
      color: black;
  }
</style>
</head>
<body>
    <h3>에러코드 : 404</h3>
    <hr>
    <p>
        요청한 페이지를 찾을 수 없습니다.<br>
        페이지 주소를 다시 한 번 확인해 주세요.
    </p>
    <p>
    	<a href="${pageContext.request.contextPath}/index.do">홈</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
        <a href="${pageContext.request.contextPath}/member/login.do">로그인</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
        <a href="${pageContext.request.contextPath}/board/list.do">게시판</a>
    </p>
</body>
</html>