package human.smart.com.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration//XML기반 설정에서 web.xml파일의 역할을 하는 설정 클래스
//1. AbstractAnnotationConfigDispatcherServletInitializer 추상클래스 상속
//2. 추상메소드 3개를 오버라이딩
//3. 필요한 일반 메소드 오버라이딩
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//root-context.xml을 대신하는 클래스를 정의함
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//servlet-context.xml을 대신하는 클래스를 정의함
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		//모든 요청을 DispatcherServlet이 받을 수 있도록 정의함
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		//POST방식으로 서버로 전달되는 값들의 한글깨짐을 방지하기 위한 필터 설정
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);

		return new Filter[] {encodingFilter};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		//파일 업로드를 위한 MultipartConfig 설정 추가하기
		MultipartConfigElement multipartConfig = 
				new MultipartConfigElement("C:\\uploads\\temp", 5242880, 20971520, 10485760);
		//임시 파일 업로드 위치 - C:\\uploads\\temp
		//파일 한 개의 최대 크기: 5MB, 한 번에 여러 파일을 올릴 때 최대 크기: 20MB, 
		//최대 크기를 넘으면 저장될 temp의 크기: 10MB
		
		registration.setMultipartConfig(multipartConfig);
		
		//404에러 처리를 위해 DispatcherServlet의 초기화 파라미터로 throwExceptionIfNoHandlerFound를
		//설정하고 값을 true로 세팅해줌
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
	}
	

}
