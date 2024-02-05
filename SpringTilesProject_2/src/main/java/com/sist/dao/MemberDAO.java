package com.sist.dao;
import com.sist.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public MemberVO isLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		// ID존재 여부 확인
		int count=mapper.idCount(id);
		if(count==0) {
			vo.setMsg("NOID");
		}
		else {
			MemberVO dbvo=mapper.isLogin(id);
			if(pwd.equals(dbvo.getPwd())) {
				vo.setMsg("OK");
				vo.setName(dbvo.getName());
				vo.setId(dbvo.getId());
			}else // 비번틀림
			{
				vo.setMsg("NOPWD");
			}
		}
		
		return vo;
		
	}
}
