package human.smart.service.board;

import org.springframework.stereotype.Service;

import human.smart.com.common.PageNav;

@Service("bPage")
public class BoardPageService implements BoardService {
	
	@Override
	public PageNav setPageNav(PageNav pageNav, int pageNum, int pageBlock) {
		int totalRows = pageNav.getTotalRows();
		int rows_page = 10;
		int pages_pageBlock = 5;
		
		pageNav.setRows_page(rows_page);
		pageNav.setPages_pageBlock(pages_pageBlock);
		
		int pNum = 0;
		if(pageNum == 0) {
			pNum = 1;
		}else {
			pNum = pageNum;
		}
		pageNav.setPageNum(pNum);
		
		int pBlock = 0;
		if(pageBlock == 0) {
			pBlock = 1;
		}else {
			pBlock = pageBlock;
		}
		pageNav.setPageBlock(pBlock);
		
		int startNum = (pNum-1)*rows_page + 1;
		int endNum = pNum*rows_page;
		int total_pageNum = (int)Math.ceil((double)totalRows/rows_page);
		int last_pageBlock = (int)Math.ceil((double)total_pageNum/pages_pageBlock);
		
		pageNav.setStartNum(startNum);
		pageNav.setEndNum(endNum);
		pageNav.setTotal_pageNum(total_pageNum);
		pageNav.setLast_pageBlock(last_pageBlock);
		
		return pageNav;
	}
	

}
