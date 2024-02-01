package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * 		셋팅 => 인식 (DispatcherServlet)
 * 
 * 					DispatcherServlet		Model
 * 		주문 =============> 서빙 ============> 주방
 * 			<============  음식  <============ 
 * 			ViewResolver		  HandlerMapping
 * 
 * 		Dispatcher에 .do로 주문을 한다 
 * 		.do를 찾아서 해당하는 Model을 거쳐서 음식이 나오면
 *  	HandlerMappping이 ViewResolver 에게 다시 가져다 준다
 *      ViewResolver에서 해당 jsp에 가져다 주거나 
 *      새로운 jsp에 가져다 준다 
 *      
 *      JSP가 없어지는 추세 => HTML 과 타임리프,JPA만 활용하는 추세 (차세대)
 *      
 *      JPA
 *      ====
 *      @Table(name="board")
 *      class Board
 *      {
 *      	@Id
 *      	private int no;   
 *      }
 *      => insert시 자동으로 번호가 증가
 *      
 *      Mobx => React(Spring) 
 *      
 */
@Configuration
@ComponentScan(basePackages = "com.sist.*")
//<context:component-scan base-package="com.sist.*"/>
@MapperScan(basePackages = {"com.sist.mapper"})
//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@EnableAspectJAutoProxy    // AOP 설정하는 Annotation
//<aop:aspectj-autoproxy/>
public class DataBoardConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	// => HandlerMapping 
	
	/*
	 * ViewResolve는 전용Anntation 이 없음 => @Bean 이용 => 아래처럼 인터페이스 활용해서 메소드 만듬
	 * <bean id="viewResolve" class="org.springframework.web.servlet.view.InternalResourceViewResolver"
		p:prefix="/"
		p:suffix=".jsp"
		scope="prototype" => 싱글톤의 반대 => 호출시에 새롭게 메모리 할당 => @scope
		/> 
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ir=new InternalResourceViewResolver();
		ir.setPrefix("/");
		ir.setSuffix(".jsp");
		return ir;
	}
	
	@Bean("multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(100*1024*1024); // 100메가
		return multipartResolver;
	}
	
	/*
	 * <bean id="ds" 
		class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="#{db['driver']}"
		p:url="#{db['url']}"
		p:username="#{db['username']}"
		p:password="#{db['password']}"
		p:maxActive="#{db['maxActive']}"
		p:maxIdle="#{db['maxIdle']}"
		p:maxWait="#{db['maxWait']}"
		/>
	 * 
	 */
	@Bean("ds")
	public DataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(50);
		ds.setMaxIdle(20);
		ds.setMaxWait(-1);
		return ds;
	}
	
	/*
	 * <bean id="ssf"
		class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		/>
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
		
	}
}







