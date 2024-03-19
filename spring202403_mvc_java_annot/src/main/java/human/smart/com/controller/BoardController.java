package human.smart.com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import human.smart.com.common.PageNav;
import human.smart.com.vo.BoardVO;
import human.smart.com.vo.SearchVO;
import human.smart.service.board.BoardService;
import human.smart.service.member.MemberService;
import lombok.Setter;

@Controller
@RequestMapping("/board")
public class BoardController {
	////////////////////////////// 필드와 의존 자동주입 //////////////////////////////
	
	//게시판 처리를 위한 Service클래스를 필드로 정의하기
	@Setter(onMethod_={ @Autowired })//Lombok API를 이용해 각 필드에 의존 자동주입을 하는 setter메소드를 정의해줌
	private BoardService bList, bPage, bInsert, bUpdateCount, bView, bUpdate,
	  bDownload, bDelete, bTotalCount;
	
	@Setter(onMethod_={ @Autowired })//Lombok API를 이용해 각 필드에 의존 자동주입을 하는 setter메소드를 정의해줌
	private PageNav pageNav;
	
	/////////////////////////////// 요청처리 메소드 /////////////////////////////////
	
	//글목록 페이지 요청
	@GetMapping("/list.do")
	public String list(@ModelAttribute("sVO") SearchVO searchVO, Model model) {
		//페이지 네비게이션을 통해서 글목록 페이지 요청시 검색 영역(searchField)과 검색어(searchWord)가 
		//전달값으로 전달되고 pageNum과 pageBlock도 전달값으로 전달됨
		//검색 영역(searchField)과 검색어(searchWord) 전달값은 SearchVO객체(커맨드 객체)를 이용함
		//글목록 화면은 tb_board에 저장된 데이터를 가져와서 boardList로 View 페이지에서 사용할 수 있도록
		//해줘야 함: Model객체 이용
			
		//@ModelAttribute(참조변수명)는 커맨드 객체를 View페이지에서 사용할 때 사용할 수 있는 참조변수명을
		//정의할 수 있음. 만약 이 어노테이션을 사용하지 않고 커맨드 객체를 View페이지에서 사용하려면 커맨드 객체의
		//첫 글자를 소문자로 해서 사용할 수 있음
			
		//게시글 목록 요청에 대해 처리할 수 있는 Service 클래스 이용: BoardListService, getBoards(searchVO)
		//페이지번호가 0인 경우 pageNum을 1로 세팅해줌
		if(searchVO.getPageNum() == 0) {
			searchVO.setPageNum(1);
		}
        List<BoardVO> boardList = bList.getBoards(searchVO);
		model.addAttribute("boardList", boardList);
		
		//총게시물 수를 PageNav클래스에 세팅하기
		//BoardMapper에서 게시물을 pageNum을 기준으로 10개만 가져오기 때문에 총 게시글 수를 가져오는 별도의 Service클래스
		//정의해줌: BoardTotalCountService클래스
		//int totRows = 0;
		//if(boardList.size() != 0) {//tb_board에 게시물이 저장된 경우
			//totRows = boardList.size();
		//}
		//pageNav.setTotalRows(totRows);//페이지 네비게이션을 위해 PageNav클래스 정의해서 사용함
		pageNav.setTotalRows(bTotalCount.getTotalCount(searchVO));
		
		//PageNav클래스에 실제적인 값을 세팅하기 위한 BoardPageServce클래스 이용
		//:setPageNav(pageNav, pageNum, pageBlock)
		pageNav = bPage.setPageNav(pageNav, searchVO.getPageNum(), searchVO.getPageBlock());
		
		//View페이지에서 pageNav객체를 사용할 수 있도록 Model객체에 추가함
		model.addAttribute("pageNav", pageNav);
		
		return "board/list";
	}
	
	//글등록 화면요청 처리
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	//글등록 요청 처리
	@PostMapping("/writeProcess.do")
	public String writeProcess(BoardVO vo, HttpServletRequest request) {
		//요청 처리 메소드의 매개변수: 글등록 페이지에서 입력된 값, 파일 업로드를 위해서
		//웹 프로그램의 uploads폴더에 대한 실제 경로를 얻기 위해서 request객체 필요	
		
		String viewPage = "baord/write"; //글등록 실패시 보여지는 페이지
		
		//글등록에 대한 요청을 BoardInsertService클래스를 이용해서 처리함
		int result = bInsert.insert(vo, request);
		
		if(result==1) {//글등록 성공시 보여지는 페이지
			viewPage = "redirect:list.do";
		}
		
		return viewPage;
	}
	
	//글내용 보기 화면요청 처리
	@GetMapping("/view.do")
	public String view(int b_idx, Model model) {
		
		//조회수 증가시키기- BoardUpdateCountService 클래스 이용
		bUpdateCount.updateReadCount(b_idx);
		
		//게시물 가져오기 - BoardViewService 클래스 이용
		BoardVO vo = bView.getBoard(b_idx);
		
		model.addAttribute("boardVO", vo);
		
		return "board/view";
	}
	
	//글 수정 화면 요청
	@GetMapping("/update.do")
	public String update(int b_idx, Model model) {
		//게시물 가져오기 - BoardViewService 클래스 이용
		BoardVO vo = bView.getBoard(b_idx);
		model.addAttribute("boardVO", vo);

		return "board/update";
	}
	
	
	//글수정 요청 처리
	@PostMapping("/updateProcess.do")
	public String updateProcess(BoardVO vo, HttpServletRequest request, Model model) {
		
		String viewPage = "baord/update"; //글수정 실패시 보여지는 페이지
		
		//첨부파일과 함께 글내용 수정을 BoardUpdateService클래스 이용
		int result = bUpdate.update(vo, request);
		
		if(result==1) {//글수정 성공시 보여지는 페이지
			BoardVO newVo = bView.getBoard(vo.getB_idx());
			model.addAttribute("boardVO", newVo);
			viewPage = "board/view";
		}
		
		return viewPage;
	}
	
	//파일 다운로드 요청 처리
	@GetMapping("/download.do")
	public void download(String originfile_name, String savefile_name,
			HttpServletRequest request, HttpServletResponse response) {
		//request객체는 파일의 실제 저장경로를 알아내는데 필요하고,
		//response객체는 파일을 출력하는데 필요함
		
		//파일 다운로드 요청 처리는 BoardDownloadService 클래스 이용
		bDownload.download(originfile_name, savefile_name, request, response);
	}
	
	//글삭제 요청 처리
	@GetMapping("/deleteProcess.do")
	public String deleteProcess(int b_idx, HttpServletRequest request) {
		//GET방식으로 전달된 값도 요청처리 메소드의 매개변수 이름을 같게 하면 받을 수 있음 
		//request객체는 세션에 저장된 회원번호를 알아내기 위해 필요함
		
		String viewPage = "board/view";//글삭제 실패시 JSP페이지
		
		//글삭제 요청 처리를 위해 BoardDeleteService 클래스 이용
		int result = bDelete.delete(b_idx, request);
		
		if(result == 1) {//글삭제 성공 시
			viewPage = "redirect:/board/list.do";
		}
		
		return viewPage;
	}
	
	
}
