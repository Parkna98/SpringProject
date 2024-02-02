package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// => 타시스템과 연결 => 자바 스크립트와 연결 (Ajax,Vue,React) => JSON/일반문자열 전송
// @ResponseBody (메소드형) => 클래스형 => @RestController
// simple-json => 직접 JSON을 만드는 과정 => 자동 완성 (JACKSON)**
// JACKSON => JSON (자바스크립트 객체 표현법) 
// 				=> {"키",값,"키",값} => 키를 멤버변수로 인식
/*
 * 	오라클 : ROW, 자바 : 객체, 자바스크립트 : {}
 */
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

@RestController
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list(int page) throws JsonProcessingException {
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<FoodVO> list=service.foodListData(start, end);
		
		// json변환해서 전달
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json; 
	}
	
	@GetMapping(value="food/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page(int page) throws JsonProcessingException{
		int totalpage=service.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	// 상세보기
	@GetMapping(value="food/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_detail(int fno) throws JsonProcessingException {
		FoodVO vo=service.foodDetailDate(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
}









