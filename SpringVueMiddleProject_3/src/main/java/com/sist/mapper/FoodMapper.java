package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
// => SQL 문장 저장 => MyBatis가 구현
public interface FoodMapper {
	@Select("SELECT fno,poster,name,num "                  
			+ "FROM (SELECT fno,poster,name,rownum as num "
			+ "FROM (SELECT fno,poster,name "              
			+ "FROM food_menu_house ORDER BY fno ASC)) " 
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start,@Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_menu_house")
	public int foodTotalPage();
	
	@Select("SELECT fno,score,poster,name,type,address,phone,theme,price,time,seat "
			+ "FROM food_menu_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailDate(int fno);
}
