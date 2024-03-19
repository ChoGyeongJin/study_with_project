package human.smart.com.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import human.smart.com.interceptor.LoginInterceptor;
import lombok.AllArgsConstructor;

/* ServletConfig.java 설정 클래스
   - XML기반 설정에서 servlet-context.xml파일의 역할을 하는 설정 클래스
   - 구현 방식
   1) @EnableWebMvc와 WebMvcConfigurer인터페이스를 상속하는 방식
   2) @Configuration과 WebMvcConfigurationSupport클래스를 상속하는 방식
   
   - 빈 등록을 위한 컴포넌트 스캔: @ComponentScan 어노테이션으로 설정
   - ViewResolver에 대한 설정: configureViewResolvers() 메소드로 설정
   - resources 디렉토리에 대한 설정: addResourceHandlers() 메소드로 설정
*/


@EnableWebMvc
@ComponentScan(basePackages= {"human.smart.com", "human.smart.service"})
@AllArgsConstructor//lombok API에서 모든 인스턴스 필드를 생성자 방식으로 의존 자동주입하도록 표시함
public class ServletConfig implements WebMvcConfigurer{
	//로그인 인터셉터 자동 빈 주입
	private LoginInterceptor loginInterceptor;
	
	@Override //ViewResolver에 대한 설정
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	
	@Override//resources 디렉토리에 대한 설정
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean//파일업로드 관련 MultipartResolver 빈 등록
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}
	
	@Override//servlet-context.xml에서 Interceptor 관련 설정에 대한 처리
	public void addInterceptors(InterceptorRegistry registry) {
		
		//로그인 인터셉터에서 처리하는 요청 URL 패턴
		String pathPattern = "/**/*.do";//**: 0개 또는 그 이상의 폴더 경로
		
		//로그인 인터셉터에서 제외되는 요청 URL
		List<String> excludes = new ArrayList<String>();
		
		//메인 페이지에 대한 요청
		excludes.add("/");
		excludes.add("/index.do");
		
		excludes.add("/member/emailCheck.do");
		excludes.add("/member/checkIdProcess.do");
		excludes.add("/member/join.do");
		excludes.add("/member/joinProcess.do");
		excludes.add("/member/login.do");
		excludes.add("/member/loginProcess.do");
		
		excludes.add("/board/list.do");
		excludes.add("/board/view.do");
		excludes.add("/board/download.do");
		
		//로그인 인터셉터를 인터셉터로 등록하고 체크하는 경로 패턴과 제외하는 경로 설정
		registry.addInterceptor(loginInterceptor).addPathPatterns(pathPattern).excludePathPatterns(excludes);
		
	}
	
	
	@Bean//비밀번호 암호화를 위해 사용되는 클래스 빈 등록
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
