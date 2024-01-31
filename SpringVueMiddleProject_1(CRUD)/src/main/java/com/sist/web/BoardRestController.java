package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.BoardDAO;

// 자바스크립트와 연동 => 데이터를 받아서 처리 후에 결과값 전송 => RestFul ***********
//												     => GET/POST/PUT/DELETE
// @Responsebody => RestController의 이전버전
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value="board/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_list_vue(int page) throws Exception{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<BoardVO> list=dao.boardListdata(start, end);
		ObjectMapper mapper=new ObjectMapper(); // JSON 자동으로 만들어줌
		String json=mapper.writeValueAsString(list); // Jackson
		
		// list_vue에서는 list를 json으로 변환해서 넘기고
		// page_vue에서는 page를 넘긴다
		// 같이 할 수 있지만 jsonarray써서 jsonobject만들고 하나씩 묶어서 넣어주는 작업
		// 저 작업보다는 이렇게 page, list를 나눠서 보내는것이 보통 더 효율적
		
		return json;
	}
	
	@GetMapping(value="board/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_page(int page) throws Exception{
		Map map=new HashMap();
		int totalpage=dao.boardTotalPage();
		
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	@PostMapping(value="board/insert_ok.do",produces = "text/plain;charset=UTF-8")
	public void board_insert_ok(BoardVO vo) {
		/*
		 * System.out.println("loading..."); String
		 * name=(String)request.getParameter("name"); String
		 * subject=(String)request.getParameter("subject"); String
		 * content=(String)request.getParameter("content"); String
		 * pwd=(String)request.getParameter("pwd"); System.out.println("loading2...");
		 * System.out.println(name); System.out.println(subject);
		 * System.out.println(content); System.out.println(pwd); BoardVO vo=new
		 * BoardVO(); vo.setContent(content); vo.setName(name); vo.setSubject(subject);
		 * vo.setPwd(pwd);
		 */
		dao.boardInsert(vo);
//		return "redirect:../board/list.do";
//		위의 리턴은 먹히지 않음 => Rest에서는 일반문자열과 json을 보내는 용도라서 주소나, redirect 인식하지 못함
//		String url="<script>"
//				+ "location.href=\"list.do\""
//				+ "</script>";
//		return url;
		// restcontroller에서는 script를 작성해서 이동시킨다!
	
	}
	
	/*
	 		@RestController
	 		class A
	 		{
	 			@Getmapper
	 			publlic List<FoodVO> listdata(){
	 				List<FoodVO> list=dao.getList(0
	 				return list;
	 			}
	 		}
	 */
	@GetMapping(value="board/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_detail(int no) throws Exception {
		BoardVO vo=dao.boardDetailData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		System.out.println(json);
		return json;
	}
	
	@GetMapping(value="board/update_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_update(int no) throws Exception{
		BoardVO vo=dao.boardUpdateData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@PostMapping(value="board/update_ok.do",produces = "text/plain;charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
		String result=dao.boardUpdate(vo);
		return result;
	}
	
	@GetMapping(value="board/delete_vue.do",produces = "text/plain;charset=UTF-8")
	public String board_delete_ok(int no,String pwd) {
		String result=dao.boardDelete(no, pwd);
		return result;
	}
}






