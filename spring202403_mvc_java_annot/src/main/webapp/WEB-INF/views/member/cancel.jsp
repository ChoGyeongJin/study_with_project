<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	let confirmAns = confirm("정말 회원 탈퇴하시겠습니까?");
	
	if(confirmAns){
		location.href = "cancel_process.do";
	}else{
		history.back();
	}
</script>    