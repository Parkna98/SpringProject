package com.sist.vo;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
/*
  		기본
  		===
  		  src
  		  	= com.sist.config : XML을 대신 스프링에 클래스 관계를 설정
  		  						=> 5버전의 핵심 (권장) => 보안 
  		  						=> Spring-Boot (포함 => SpringFramework) => XML(pom.xml) => Maven
  		  																	=> db.properties => Gradle
  		  						=> 순수하게 자바로만 설정 (xml대신 annotation)
  		  						   <context:component-scan>
  		  						   => @ComponentScan()
  		  						   
  		  						   <mybatis-spring>
  		  						   => @MapperScan()
  		  						   
  		  						   <bean>
  		  						   @Bean
  		  						    
  		  	= com.sist.vo : 사용자정의 데이터형
  		  	= com.sist.dao : 데이터베이스 연결
  		  	= com.sist.service : DAO 통합 => interface (결합성 낮게)
  		  	= com.sist.web : Model (요청 => 응답)
  		  	= com.sist.interceptor (Model 연결 전, JSP 연결 전)
  		  							preHandle()   postHandle()
  		  							=> 자동 로그인   => 권한
  		  	= com.sist.aop : 공통모듈
  		  	= com.sist.manager : OPEN API (실시간 뉴스 읽기)
  		  	= com.sist.commons : 공통 예외처리
  		  	= com.sist.security : 보안 => 암호화 / 복호화 / 권한 / 메소드 보안
  		  	= com.sist.chat : 실시간 상담
  		  	
  		  	=> 모든 패키지의 메모리 할당 요청 ==> 스프링 (클래스 관리) => 생성 / 소멸
  		  												       =========
  		  												       1. 스프링에 클래스 메모리 주소를 요청
  		  												       	 	=> @Autowired
  		  	=> 서블릿 (자바로 만들어진 웹 파일)
  		  	   server + let => servlet
  		  	   => 서버에서 실행하는 가벼운 프로그램											       	 	
  		  	   => 톰캣에서 서블릿을 실행한다 (request/response)	
  		  	   	  ======================================== 웹서버
  		  	   	  톰캣에는 웹서버가 있다 (우리가 만들거나 생성할수없어서 톰캣에서 생성한다)
  		  	   	  => 서블릿은 무조건 톰캣에 등록
  		  	   	  				 =========
  		  	   	  				  web.xml => @WebServlet("*.do") 
  		  	   	  				  (xml이용하거나 @WebServlet 이용)
  		  	   	  				  
  		  	   	  => DispatcherServlet 등록
  		  	   	  	 => WebApplicationContext
  		  	   	  	 => HandlerMapping
  		  	   	  	 => XML => 자동 생성, 어노테이션은 직접 생성
  		  	   	  	 => URL에 따라서 => 톰캣이 관리
  		  	   	  	 
  		     list.do ===============================> DispatcherServlet
  		     		 톰캣(request,response)
  		     		 	public void service(request,response
  		     		 	{
  		     		 	|	=> Model찾기 ==> HandlerMappring
  		     		 			=>  기능 수행 (메소드) => 구분자 (@Getting/@PostMapping)
  		     		 		=> return을 읽어온다
  		     		 		=> JSP 찾기 ==> ViewResolver(경로명)
  		     		 		=> request
  		     		 	}
  		     		 	
  		     		    필요 세팅을 직접 해야지 구동된다
  		     		    MVC구조에 대한 질문을 많이 하므로 완전하게 익히고 갈것!

  		  	
 */
@Data
public class DataBoardVO {
	private int no,hit,filecount;
	private String name,subject,content,pwd,filename,filesize,dbday;
	private Date regdate;
	private List<MultipartFile> files;
}
