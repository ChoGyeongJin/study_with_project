package human.smart.com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import human.smart.com.vo.MemberVO;

@Component//컴포넌트 스캔의 대상이 되어서 빈 등록이 이루어짐
public class LoginInterceptor implements HandlerInterceptor {
	//HandlerInterceptor 인터페이스에서 JDK8 이후부터는 3개의 메소드를 디폴트 메소드로 정의해 둠
	//preHandle(), postHandle(), afterCompletion()
	//이들 가운데 필요한 메소드를 Override해서 사용함

	@Override//사용자 요청이 Controller로 전달되기 전에 요청을 가로채어서 메소드 실행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean result = true;//Controller로 사용자 요청이 전달되게 하는 반환값
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		if(member == null) {//로그인이 안된 경우
			response.sendRedirect(request.getContextPath()+"/member/login.do");
			result = false;//Controller로 사용자 요청이 전달되지 못하도록 하는 반환값
		}
		
		return result;
	}

}