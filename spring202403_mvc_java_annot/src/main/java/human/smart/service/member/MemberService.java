package human.smart.service.member;

import human.smart.com.vo.MemberVO;

public interface MemberService {

	default int join(MemberVO memberVo) {return 0;}
	default int checkId(String member_id) {return 0;}
	default MemberVO login(String member_id, String member_pw) {return null;}
	default MemberVO update(MemberVO memberVo) {return null;}
	default int cancel(int member_idx) {return 0;}
	default String joinEmail(String email) {return null;}
}
