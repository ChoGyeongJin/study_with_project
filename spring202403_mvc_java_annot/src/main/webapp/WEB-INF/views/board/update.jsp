<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/update.css">
<style>
input[type=file]{
	display: none;
	border-color: white;
}
#span-file-btn{
	width:80px; 
	height:20px;
	lineHeight:20px;
	padding:5px 0;
	textAlign:center; 
	backgroundColor:orange; 
	borderRadius:20px; 
	font:bold 13px Arial;sans-serif;
	color:white;
	cursor: pointer
}
#span-fileName{
	font: 12px Arial,sans-serif;
}
</style>

<script>
	$(function(){
		$("#div-file-btn").css(
			{  
				display: "inline-block",
                width:"80px", 
                height:"25px",
                paddingTop:"5px",
                textAlign:"center", 
                backgroundColor:"orange", 
                borderRadius:"20px", 
                font:"12px Arial,sans-serif",
                color:"white",
                cursor: "pointer"
              }
		);
		
		//파일창에서 선택한 파일명 출력하기:  text() 메소드
        $("input:file").on("change", function(){
            $("#span-fileName").text(this.files[0].name);
        });
	});

</script>

</head>
<body>

<div id="wrap">
	<%@ include file="../common/header2.jsp" %>

<section>
	<h3>글수정</h3>
	<div id="container">
		<form name="frm_update" method="post" action="updateProcess.do" enctype="multipart/form-data">
			<input type="hidden" name="b_idx" value="${boardVO.b_idx}" >
			<input type="hidden" name="m_idx" value="${boardVO.m_idx}" >
			<input type="hidden" name="writer" value="${boardVO.writer}" >
			작성자: <input type="text" value="${boardVO.writer}" disabled> <br>
			제&nbsp;&nbsp;목: <input type="text" name="title" value="${boardVO.title}"> <br>
			내용<br>
   		    <textarea name="content" cols="30" rows="10"> ${boardVO.content} </textarea> <br>
			첨부파일: (새로운 파일을 선택하면 이전 파일이 교체됩니다)<br><br>
			<label><input type="file" name="uploadFile"><div id="div-file-btn">파일첨부</div></label>
   				    <span id="span-fileName"></span>
			<div id="div-bundle-btn">
				<input type="submit" value="수정하기" >
				<input type="reset" value="다시입력" >
				<input type="button" value="목록보기" onclick="location.href='list.do'" >
			</div>
		</form>
	</div>
</section>

<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html>