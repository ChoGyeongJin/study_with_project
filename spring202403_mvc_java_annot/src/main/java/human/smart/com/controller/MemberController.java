package human.smart.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import human.smart.com.vo.MemberVO;
import human.smart.service.member.MemberService;
import lombok.Setter;

@Controller
@RequestMapping("/member")
public class MemberController {
//회원관리시스템 관련 요청에 대해 처리하는 컨트롤러.
//사용자의 요청에 views의 member폴더가 경로에 포함되어 전달되므로
//중복된 경로를 컨트롤러 상단에 @RequestMapping("/member")로 처리함
//컨트롤러 내에 정의되는 요청매핑은 모두 앞에 /member가 붙는 것으로 
//보면 됨
	
	//Controller는 DispatcherServlet으로부터 받은 사용자의 요청을
	//직접 처리하지 않고 Service클래스로 요청처리를 넘김
	//MemberService인터페이스의 하위 클래스로 각 서브스클래스를 정의해줌

	////////////////////////////// 필드와 의존 자동주입 //////////////////////////////
	
	@Setter(onMethod_={ @Autowired })//Lombok API를 이용해 각 필드에 의존 자동주입을 하는 setter메소드를 정의해줌
	private MemberService mJoin, mLogin, mUpdate, mCancel;

	/////////////////////////////// 요청처리 메소드 /////////////////////////////////	
	
	//회원가입 페이지 요청
	@GetMapping("/join.do")
	public String join() {
		return "member/join";
	}
	
	//회원가입 처리 요청
	@PostMapping("/joinProcess.do")
	public String joinProcess(MemberVO memberVo) {
	//커맨드 객체: 폼의 입력값 전송을 처리하는 메소드에서 파라미터 값들을 저장하는데 사용되는 자바 객체
	//-파라미터 값들을 전달받을 수있도록 setter메소드를 포함하고 있음
	//-폼의 name 속성값과 일치하는 필드에 입력값을 저장함
	//-뷰에서 커맨드 객체를 사용하려면 첫글자를 소문자로 바꾼 클래스 이름으로 사용할 수 있음
		
		int result = mJoin.join(memberVo);
		
		String viewPage="member/join";//회원가입 실패시 JSP페이지
		if(result == 1) {//정상적으로 회원가입이 이루어진 경우 메인 메소드 재요청
			viewPage = "redirect:/index.do";
			//view이름으로 "redirect:요청정보"가 전달되면 요청정보로 재요청이 이루어짐
		}
		
		return viewPage;
	}
	
	//로그인 페이지 요청
	@GetMapping("/login.do")
	public String login() {
		return "member/login";
	}
	
	//로그인 처리 요청
	@PostMapping("/loginProcess.do")
	public String loginProcess(String member_id, String member_pw, 
							   HttpServletRequest request, Model model) {
		
		//로그인 처리를 할 MemberLoginService클래스를 이용함
		MemberVO vo = mLogin.login(member_id, member_pw);
		
		String viewPage = "member/login";//로그인 실패시 JSP페이지
		if(vo != null){//로그인 성공시
			//세션객체에 회원 정보를 추가함(추후 사용을 위해 회원정보를 MemberVo객체로 저장함)
			HttpSession session = request.getSession();
			session.setAttribute("member", vo);
			viewPage = "redirect:/index.do";//메인 메소드 재요청

		}else{//로그인 실패시
			model.addAttribute("msg","회원정보가 일치하지 않습니다");
		}
		
		return viewPage;
	}
	
	//로그아웃 처리
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();//세션 초기화
		
		return "redirect:/index.do";
	}
	
	//회원정보 변경페이지 요청 처리
	@GetMapping("/update.do")
	public String update() {
		return "member/update";
	}
	
	//회원정보 변경 처리
	@PostMapping("/updateProcess.do")
	public String updateProcess(MemberVO memberVo, HttpServletRequest request, Model model) {
	//커맨드 객체: 폼의 입력값 전송을 처리하는 메소드에서 파라미터 값들을 저장하는데 사용되는 자바 객체
		
		//MemberUpdateService클래스를 이용한 회원정보 수정을 처리
		MemberVO vo = mUpdate.update(memberVo);
		
		String viewPage="member/update";//회원정보 변경 실패시 JSP페이지
		
		if(vo != null){//회원정보 수정 성공시
			HttpSession session = request.getSession();
			session.removeAttribute("member");
			session.setAttribute("member", vo);
			viewPage = "redirect:/index.do";
		}else{//회원정보 변경 실패시
			model.addAttribute("msg","시스템 오류로 회원정보 변경이 이루어지지 않았습니다.");
		}
		
		return viewPage;
	}
	
	//회원탈퇴 요청 처리
	@GetMapping("/cancelProcess.do")
	public String cancelProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("member");
		int m_idx = vo.getM_idx();
		
		//MemberCancelService클래스를 이용한 회원탈퇴 처리
		int result = mCancel.cancel(m_idx);
		
		String viewPage = "member/update";//회원탈퇴 실패시 JSP페이지
		if(result == 1){//회원탈퇴 성공시
			session.invalidate();
			viewPage = "redirect:/index.do";
		}
		
		return viewPage;
	}
	

	
	
	
}
