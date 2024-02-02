package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

// 구현된 메퍼를 사용하는 클래스
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(int start, int end){
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	public FoodVO foodDetailDate(int fno) {
		return mapper.foodDetailDate(fno);
	}
}
