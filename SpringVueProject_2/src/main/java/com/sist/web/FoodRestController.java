package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// Vue로 데이터를 전송 => [],{}
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	// JSON => text/plain, HTML => text/html
	// => {키:값, 키:값, ... } 
	@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8") 
	public String food_list_vue() throws Exception{
		List<FoodVO> list=dao.foodListData();
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="food/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_detail_vue(int fno) throws Exception{
		FoodVO vo=dao.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
}
