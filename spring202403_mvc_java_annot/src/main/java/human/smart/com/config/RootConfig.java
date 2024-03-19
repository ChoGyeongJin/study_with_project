package human.smart.com.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration//XML기반 설정에서 root-context.xml파일의 역할을 하는 설정 클래스
@MapperScan("human.smart.com.mapper")
public class RootConfig {
	
	//MySQL관련 DB 설정
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/smartdb"
				+ "?characterEncoding=UTF-8"
				+ "&useSSL=false"
				+ "&allowPublicKeyRetrieval=true"
				+ "&serverTimezone=UTC");
		dataSource.setUsername("smart_dev");
		dataSource.setPassword("1234");
		
		return dataSource;
	}
	
	//Oracle관련 DB 설정
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
//		dataSource.setUsername("smart_dev");
//		dataSource.setPassword("1234");
//		
//		return dataSource;
//	}
	
	@Bean//MyBatis 관련 빈 등록
	public SqlSessionFactory seqSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		
		return (SqlSessionFactory)sqlSessionFactory.getObject();
	}
	
	@Bean//이메일 인증 관련 빈 등록
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.naver.com");
		mailSender.setPort(465);
		mailSender.setUsername("네이버계정");
		mailSender.setPassword("네이버비번");
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("구글계정");
//		mailSender.setPassword("구글2단계비번");
		
		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.transport.protocol", "smtp");
		mailProperties.setProperty("mail.smtp.auth", "true");
		mailProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProperties.setProperty("mail.smtp.starttls.enable", "true");
		mailProperties.setProperty("mail.debug", "true");
		mailProperties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
//		mailProperties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		mailProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
		
	}
	
	

}
