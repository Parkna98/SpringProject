package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import java.util.*;
@Repository
public class FoodDAO {
	@Autowired // 스프링에서 자동 주입 
	private FoodMapper mapper; // 메모리 할당 완료
	
	public List<FoodVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
