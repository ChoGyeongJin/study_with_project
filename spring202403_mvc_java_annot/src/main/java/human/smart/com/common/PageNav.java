package human.smart.com.common;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PageNav {
	/*페이지 네비게이션 관련 변수 선언
	1. 총 레코드 수: totalRows
	2. 페이지당 테이블에 출력할 레코드 수: rows_page
	3. 페이지 네비게이션에서 블록당 페이지 수: pages_pageBlock
	4. 페이지 네비게이션에서 현재 페이지 번호: pageNum
	5. 테이블에 표시할 시작번호: startNum
	6. 테이블에 표시할 끝번호: endNum
	7. 페이지 네비게이션에서 전체 페이지번호 수: total_pageNum
	8. 페이지 네비게이션에서 현재 페이지 블록: pageBlock
	9. 페이지 네비게이션에서 마지막 페이지 블록: lage_pageBlock
	*/
	
	private int totalRows;
	private int rows_page;
	private int pages_pageBlock;
	private int pageNum;
	private int startNum;
	private int endNum;
	private int total_pageNum;
	private int pageBlock;
	private int last_pageBlock;
	
	
}
