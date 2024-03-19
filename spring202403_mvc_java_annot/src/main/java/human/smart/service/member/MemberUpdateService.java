package human.smart.service.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import human.smart.com.mapper.MemberMapper;
import human.smart.com.vo.MemberVO;
import lombok.AllArgsConstructor;

@Service("mUpdate")
@AllArgsConstructor
public class MemberUpdateService implements MemberService {
	private MemberMapper memberMapper;
	private BCryptPasswordEncoder cryptPasswordEncoder;
	
	@Override
	public MemberVO update(MemberVO memberVO) {
		MemberVO newVO = null;//회원가입 실패시 결과값
		String member_pw = memberVO.getMember_pw();
		
		//회원정보 중 아이디를 이용해서 암호화된 비밀번호 가져오기
		String encodePassword = memberMapper.getPassword(memberVO.getMember_id());
		
		if(member_pw.length() != 0) {//회원정보 수정페이지에서 새 비밀번호를 입력한 경우
			String newPassword = cryptPasswordEncoder.encode(member_pw);
			memberVO.setMember_pw(newPassword);
			
		}else {//새 비밀번호를 입력하지 않은 경우
			memberVO.setMember_pw(encodePassword);
		}
		
		if(memberMapper.updateMember(memberVO) == 1) {//회원정보 업데이트 성공
			newVO = memberMapper.getMember(memberVO.getM_idx());
		}
			
		return newVO;
	}


}
