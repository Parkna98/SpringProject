package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.*;
public interface GoodMapper {
	@Select("SELECT no,goods_name,goods_poster,num "
			+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
			+ "FROM (SELECT no,goods_name,goods_poster "
			+ "FROM goods_all ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
}
