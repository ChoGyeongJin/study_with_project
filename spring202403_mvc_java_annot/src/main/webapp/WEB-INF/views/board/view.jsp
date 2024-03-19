<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
<link rel="stylesheet" href="../resources/css/view.css">
<script>
	function deletePost(){
		const ans = confirm("정말로 삭제하겠습니까?");
		//예:true, 아이오:false
		
		if(ans){
			location.href="deleteProcess.do?b_idx=${boardVO.b_idx}";
		}
	}

</script>

</head>
<body>
<div id="wrap">
	<%@ include file="../common/header2.jsp" %>

<section>
	<h3>글내용</h3>
	<div id="container">
		<div class="div-content"><span class="span-header">작성자:</span> ${boardVO.writer}</div>
		<div class="div-content"><span class="span-header">제&nbsp;&nbsp;&nbsp;목:</span> ${boardVO.title} </div>
		<div class="div-content"><span class="span-header">내&nbsp;&nbsp;&nbsp;용:</span> ${boardVO.content} </div>
		<div class="div-content"><span class="span-header">조회수:</span> ${boardVO.read_cnt} </div>
		<div class="div-content"><span class="span-header">작성일:</span><fmt:formatDate value="${boardVO.post_date}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/></div>
		<div class="div-content"><span class="span-header">첨부파일:</span> ${boardVO.originfile_name} </div>
	</div>
	<div id="div-bundle-btn">
		<!-- 수정하기, 삭제하기 버튼은 회원이면서 본인이 작성한 게시글일 때 화면에 출력되도록 함 -->
		<c:if test="${(not empty member) and (member.m_idx eq boardVO.m_idx)}">
			<input type="button" value="수정하기" onclick="location.href='update.do?b_idx=${boardVO.b_idx}'">
			<input type="button" value="삭제하기" onclick="deletePost()">
		</c:if>
		<input type="button" value="목록보기" onclick="location.href='list.do'" >
	</div>
</section>

<%@ include file="../common/footer.jsp" %>

</div>
</body>
</html>