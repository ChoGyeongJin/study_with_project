package human.smart.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import human.smart.com.dao.BoardDAO;
import human.smart.com.vo.BoardVO;
import human.smart.com.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service("bDelete")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class BoardDeleteService implements BoardService {
	private BoardDAO dao;
	
	@Override
	public int delete(int b_idx, HttpServletRequest request) {
		int result = 0; //삭제 실패시 반환값
		
		BoardVO vo = dao.getBoard(b_idx);
		HttpSession session = request.getSession();
		
		//로그인된 회원의 member_idx 얻기
		MemberVO mVo = (MemberVO)session.getAttribute("member");
		int m_idx = mVo.getM_idx();
		
		if(m_idx == vo.getM_idx()) {//작성자가 회원 본인인 것을 다시 확인
			result = dao.delete(vo.getB_idx());
		}
		
		return result;
	}
	

}
