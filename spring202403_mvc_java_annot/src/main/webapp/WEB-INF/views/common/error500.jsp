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
    <h3>에러코드 : 500</h3>
    <hr>
    <p>
        요청한 페이지와 관련하여 시스템에 오류가 있습니다.<br>
        빠른 시일 내에 정상적인 서비스가 되도록 하겠습니다.
    </p>
    <p>
    	<a href="${pageContext.request.contextPath}/index.do">홈</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
        <a href="${pageContext.request.contextPath}/member/login.do">로그인</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
        <a href="${pageContext.request.contextPath}/board/list.do">게시판</a>
    </p>    
</body>
</html>