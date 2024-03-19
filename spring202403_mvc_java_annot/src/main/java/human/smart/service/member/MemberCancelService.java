package human.smart.service.member;

import org.springframework.stereotype.Service;

import human.smart.com.mapper.MemberMapper;
import lombok.AllArgsConstructor;

@Service("mCancel")
@AllArgsConstructor//모든 인스턴스 필드를 파라미터로 받는 생성자를 정의해서 의존 자동주입해줌
public class MemberCancelService implements MemberService {
	private MemberMapper memberMapper;
	
	@Override
	public int cancel(int m_idx) {
		int result = 0;//계정삭제 실패시 결과값
		result=memberMapper.cancel(m_idx);
		
		return result;
	}
}
