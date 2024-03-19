<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="../resources/css/login.css">
    <script type="text/javascript" src="../resources/js/login.js"></script>
    
    <style>
    	#td-msg{
    		padding-left: 7px;
    	}
    	#span-msg{
    		font: 13px Arial, sans-serif;
			color: orange;
		}
    </style>
    
</head>
<body>
	
    <div id="wrap">
        
        <%@ include file="../common/header2.jsp" %>
        
    <div id="container">
        <div id="logo"></div>
	    <form name="frm_login" method="post" action="loginProcess.do">
	    <!-- loginProcess.do 요청은 폴더 경로를 추가해서 요청URL로 전달됨 : /member/loginProcess.do -->
	        <table>
	            <tr>
	                <td><input type="text" name="member_id" id="member_id" placeholder="아이디"></td>
	            </tr>
	            <tr>
	                <td><input type="password" name="member_pw" id="member_pw" placeholder="비밀번호"></td>
	            </tr>
	            <tr>
	                <td><input type="button" id="login_btn" value="로 그 인"></td>
	            </tr>
	            <tr>
	                <td id="td-msg">
		                <c:if test="${not empty msg}">
					    	<span id="span-msg">${msg}</span>
					    </c:if>
	                </td>
	            </tr>
	        </table>       
	    </form>
	</div>
	
	  <%@ include file="../common/footer.jsp" %>
    
    </div>
</body>
</html>