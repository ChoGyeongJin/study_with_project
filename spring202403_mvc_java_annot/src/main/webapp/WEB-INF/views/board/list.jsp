<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<!--  
	<< 페이지 네비게이션 구현을 위한 변수들 >>
	
	1. 총 게시물수 : totalRows
	2. 한 페이지에 보여줄 게시물 수 : rows_page 
	   - 개발자가 정함 (10)
	3. 한 페이지블록에 보여줄 페이지 수 : pages_pageBlock
	   - 개발자가 정함 (5)
	4. 현재 페이지번호 : pageNum
	   - pageNum 전달값이 null인 경우 1로 초기화함
	5. 게시물의 시작번호 : startNum
	   - (현재 페이지번호 -1)*10 + 1
	6. 게시물의 끝번호 : endNum
	   - 현재 페이지번호*10
	7. 현재 페이지블록번호 : pageBlock
	   - pageBlock 전달값이 null인 경우 1로 초기화함
	8. 총/마지막 페이지번호 : total_pagNum
	   - (총 게시물수 / 한 페이지에 보여줄 게시물 수)의 올림값
	9. 마지막 페이지블록번호 : last_pageBlock
	   - (총 페이지번호 / 한 페이지블록에 보여줄 페이지 수)의 올림값
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link rel="stylesheet" href="../resources/css/list.css">

<style>
	#td_paging{
		height: 70px;
		font: 12px Arial, Sans-serif;
		text-align: center;
		border-color: white;
	}
</style>


</head>
<body>

<div id="wrap">
	<%@ include file="../common/header2.jsp" %>

<h3>글목록</h3>
<!-- 검색 폼 -->
<form>
    <table id="tbl_search">
        <tr>
            <td id="td_total">
            	총게시물수: ${pageNav.totalRows}
            </td>
            <td id="td_search">
                <select name="searchField">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="writer">작성자</option>
                </select>
                <input type="text" name="searchWord" id="searchWord">
                <input type="submit" id="search_btn" value="검색">

            </td>
        </tr>
    </table>
</form>



<table id="tbl_list">
	<tr>
		<th>구분</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>첨부파일</th>
	</tr>

	<!-- DB에 저장된 데이터 출력하기: 반복문을 이용(forEach 태그) -->
	<!-- DB에 저장된 데이터가 있는지 여부를 확인해서 각각 출력되도록 해줌: 조건문을 이용(choose, when, otherwise 태그)
	     - EL의 empty연산자는 null, 배열의 길이가 0인 경우, 컬렉션의 size가 0인 경우 true를 반환함
	  -->
	  
	<c:choose>
		<c:when test="${empty boardList}">
			<tr><td colspan="6">등록된 게시물이 없습니다</td></tr>
		</c:when>
		<c:otherwise>
			<!-- ROWNUM을 이용해서 10개씩만 가져오기 때문에 출력 부분을 변경해줘야 함 
				 baordList에는 저장된 데이터를 가져올 때 forEach태그의 varStatus의 count속성을 이용함
			-->
			<c:forEach var="i" begin="${pageNav.startNum}" end="${pageNav.endNum}" varStatus="vs">
				<c:if test="${not empty boardList[vs.count-1]}">
					<tr>
						<td>${i}</td>
						<td>
						   <a href="view.do?b_idx=${boardList[vs.count-1].b_idx}"> ${boardList[vs.count-1].title} </a>
						</td>
						<td>${boardList[vs.count-1].writer}</td>
						<td><fmt:formatDate value="${boardList[vs.count-1].post_date}" type="date" pattern="yyyy-MM-dd"/></td>
						<td>${boardList[vs.count-1].read_cnt}</td>
						<td>
							<!-- 첨부파일이 있는 경우에는 정해진 이미지를 출력시키고 없는 경우에는 공란으로 처리함 -->
							<c:if test="${not empty boardList[vs.count-1].originfile_name}">
								<a href="download.do?originfile_name=${boardList[vs.count-1].originfile_name}&savefile_name=${boardList[vs.count-1].savefile_name}">
									<img src="../resources/css/img/download.png" alt="첨부파일 이미지" width="15px" height="17px">
								</a>
							</c:if>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			
		</c:otherwise>
	</c:choose>
	
	<!-- 회원인 경우 목록 하단에 글등록 버튼 구현 -->
	<c:if test="${not empty member}">
		<tr>
			<td id="td_write_btn" colspan="6">
					
				<a href="write.do"><input type="button" id="write_btn" value="글쓰기"></a>
					
			</td>
		</tr>
	</c:if>
	
	<!-- 게시글이 있는 경우 페이지 네비게이션 구현 -->
	<c:if test="${not empty boardList}">
		<tr>
			<td id="td_paging" colspan="6">
				<!-- 페이지 네비게이션 구현 -->
				<%@ include file="paging.jsp" %>
			</td>
		</tr>
	</c:if>  

</table>

	<%@ include file="../common/footer.jsp" %>

</div>

</body>
</html>