package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.FoodDAO;
//결합성이 낮게 만들기
//인터페이스를 통해 dao를 가져온다
import com.sist.vo.FoodVO;

//BI => DAO / Manager 를 여러개 통합해서 사용 => 결합성이 낮은 프로그램 => 스프링에서 권장
import java.util.*;
//유지보수를 할 경우에 => 다른 클래스에 영향
//DAO는 유지보수가 많은 항목인데 인터페이스를 통해 가져가게 함으로써
//DAO를 수정하고 있어도 Controller에서 오류나지 않도록 한다
//결합성이 낮은 프로그램

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO dao;

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return dao.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailDate(int fno) {
		// TODO Auto-generated method stub
		return dao.foodDetailDate(fno);
	}
}
