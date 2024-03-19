<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <!-- jQuery를 사용하기 위한 설정 -->
	<script src="../resources/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/join.js"></script>
    <link rel="stylesheet" href="../resources/css/join_update.css">
    
</head>
<body>

    <div id="wrap">
    	<%@ include file="../common/header2.jsp" %>
        
    <div id="container">
        <div id="logo"></div>
        <!-- 회원가입정보를 서블릿에서 처리하도록 한 것을 jsp에서 처리하도록 변경함
             현재 폴더에 있는 join_process.jsp에서 회원정보를 처리하도록 함
         -->
	    <form name="frm_join" method="post" action="joinProcess.do">
	    <!-- joinProcess.do 요청은 폴더 경로를 추가해서 요청URL로 전달됨 : /member/joinProcess.do -->
	        <table>
	            <tr>
	                <td>
	                	<input type="text" name="member_id" id="member_id" placeholder="아이디">
	                </td>
	            </tr>
	            <tr>
	                <td><input type="password" name="member_pw" id="member_pw" placeholder="비밀번호"></td>
	            </tr>
	            <tr>
	                <td><input type="text" name="member_name" id="member_name" placeholder="이름"></td>
	            </tr>
	            <tr>
	                <td><input type="text" name="handphone" id="handphone" placeholder="핸드폰"></td>
	            </tr>
	            <tr>
	                <td>
	                	<div class="div-email">
	                		<input type="email" name="email" id="email" placeholder="이메일">
	                		<input type="button" id="email_auth_btn" value="메일인증" >
	                	</div>
	                	<div class="div-email">
	                		<input type="text" id="auth_num_input" placeholder="인증번호 6자리를 입력해주세요" 
						  	disabled = "disabled" maxlength="6">
							<input type="button" id="confirm_email_btn" value="인증확인">
	                	</div>
						<input type="hidden" name="result_confirm" id="result_confirm">
						<span id="mail-check-result"></span>
	                </td>
	            </tr>
	            <tr>
	                <td><input type="submit" id="join_btn" value="가입하기"></td>
	            </tr>
	        </table>       
	    </form>
	</div>
	
	<%@ include file="../common/footer.jsp" %>
	
	</div>
</body>
</html>