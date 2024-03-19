<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 

<<페이지 네비게이션 변수를 이용해서 페이지 네이게이션 구현하기>>

1. 총 페이지번호와 페이지블록을 연계하여 페이지번호 출력
   - 시작번호: (pageBlock-1)*pages_pageBlock+1
   - 끝번호: pageBlock*pageBlock
   - (조건) 페이지번호가 총 페이지번호보다 작거나 같을 경우 페이지 번호 출력
   - 페이지번호: a태그에 pageNum, pageBlock 값을 전달함
   
2. 총 페이지번호가 페이지블록을 초과하는 경우 '다음'과 맨마지막페이지 기호(>>) 출력
   - 현재 페이지 블록의 끝번호를 초과하는 경우
   - 다음: a태그에 현재 페이지블록+1을 pageBlock값으로 전달하고 pageNum에는 현재페이지블록*pages_pageBlock+1값을 전달함
   - 맨마지막페이지 기호: a태그에 pageNum에는 totalPageNum을, pageBlock에는 lastPageBlock 값을 전달함
   
3. 현재 페이지번호가 블록당 페이지수보다 큰 경우 '이전'과 맨처음페이지 기호(<<) 출력
   - 이전: a태그에 pageNum에는 (pageBlock-1)*pages_pageBlock+1을, pageBlock에는 pageBlock-1값을 전달함
   - 맨처음페이지 기호: a태그에 pageNum과 pageBlock에 모두 1을 전달함 
   
4. 검색어가 있는 경우와 없는 경우를 구분하여 출력
   - 검색어에 대한 변수 선언:searchField, searchWord 	
	
-->

<!-- 4. 검색어가 있는 경우와 없는 경우를 구분하여 출력 -->
<!-- 게시글 목록 요청 처리 메소드에 정의된 커맨드 객체는 JSP페이지에서 사용 가능함
     커맨드 객체 타입의 첫글자를 소문자로 해서 사용하거나 @ModelAttribute(참조변수명)을 
     매개변수 앞에 붙여서 정의된 참조변수명으로 사용가능함
 -->
<c:set var="searchField" value="${sVO.searchField}" />
<c:set var="searchWord" value="${sVO.searchWord}" />

<c:choose>
	<c:when test="${not empty searchWord}"><!-- 검색어가 있는 경우 -->
	
		<!-- 3. 현재 페이지블록이 1이 아닌 경우 '이전'과 맨처음페이지 기호(<<) 출력 -->
		<c:if test="${pageNav.pageNum gt pageNav.pages_pageBlock}">
			<a href="list.do?pageNum=1&pageBlock=1&searchField=${searchField}&searchWord=${searchWord}"> &lt;&lt; </a>
			<a href="list.do?pageNum=${(pageNav.pageBlock-2)*pageNav.pages_pageBlock+1}&pageBlock=${pageNav.pageBlock-1}&searchField=${searchField}&searchWord=${searchWord}"> 이전 </a> &nbsp;	
		</c:if>
		
		<!-- 1. 총 페이지번호와 페이지블록을 연계하여 페이지번호 출력 -->
		<c:forEach var="p" begin="${(pageNav.pageBlock-1)*pageNav.pages_pageBlock+1}" end="${pageNav.pageBlock*pageNav.pages_pageBlock}" >
			<c:if test="${p le pageNav.total_pageNum}">
				<a href="list.do?pageNum=${p}&pageBlock=${pageNav.pageBlock}&searchField=${searchField}&searchWord=${searchWord}">
					<c:if test="${p eq pageNav.pageNum}" var="flag"><!-- 페이지번호가 현재 페이지번호인 경우 -->
						<span style="color:red">${p}&nbsp;</span>
					</c:if>
					<c:if test="${not flag}">
						${p}&nbsp;
					</c:if>
				</a>
			</c:if>
		</c:forEach>
		
		<!-- 2. 총 페이지번호가 페이지블록을 초과하는 경우 '다음'과 맨마지막페이지 기호(>>) 출력  -->
		<c:if test="${pageNav.total_pageNum > (pageNav.pageBlock*pageNav.pages_pageBlock)}">
			<a href="list.do?pageNum=${(pageNav.pageBlock*pageNav.pages_pageBlock)+1}&pageBlock=${pageNav.pageBlock+1}&searchField=${searchField}&searchWord=${searchWord}"> 다음 </a> &nbsp;
			<a href="list.do?pageNum=${pageNav.total_pageNum}&pageBlock=${pageNav.last_pageBlock}&searchField=${searchField}&searchWord=${searchWord}"> &gt;&gt; </a>	
		</c:if>	
	
	</c:when>
	
	
	<c:otherwise><!-- 검색어가 없는 경우 -->

		<!-- 3. 현재 페이지블록이 1이 아닌 경우 '이전'과 맨처음페이지 기호(<<) 출력 -->
		<c:if test="${pageNav.pageNum gt 5}">
			<a href="list.do?pageNum=1&pageBlock=1"> &lt;&lt; </a>
			<a href="list.do?pageNum=${(pageNav.pageBlock-2)*pageNav.pages_pageBlock+1}&pageBlock=${pageNav.pageBlock-1}"> 이전 </a> &nbsp;	
		</c:if>
		
		<!-- 1. 총 페이지번호와 페이지블록을 연계하여 페이지번호 출력 -->
		<c:forEach var="p" begin="${(pageNav.pageBlock-1)*pageNav.pages_pageBlock+1}" end="${pageNav.pageBlock*pageNav.pages_pageBlock}" >
			<c:if test="${p le pageNav.total_pageNum}">
				<a href="list.do?pageNum=${p}&pageBlock=${pageNav.pageBlock}">
					<c:if test="${p eq pageNav.pageNum}" var="flag"><!-- 페이지번호가 현재 페이지번호인 경우 -->
						<span style="color:red">${p}&nbsp;</span>
					</c:if>
					<c:if test="${not flag}">
						${p}&nbsp;
					</c:if>
				</a>
			</c:if>
		</c:forEach>
		
		<!-- 2. 총 페이지번호가 페이지블록을 초과하는 경우 '다음'과 맨마지막페이지 기호(>>) 출력  -->
		<c:if test="${pageNav.total_pageNum > (pageNav.pageBlock*pageNav.pages_pageBlock)}">
			<a href="list.do?pageNum=${(pageNav.pageBlock*pageNav.pages_pageBlock)+1}&pageBlock=${pageNav.pageBlock+1}"> 다음 </a> &nbsp;
			<a href="list.do?pageNum=${pageNav.total_pageNum}&pageBlock=${pageNav.last_pageBlock}"> &gt;&gt; </a>	
		</c:if>	
	
	</c:otherwise>
</c:choose>

