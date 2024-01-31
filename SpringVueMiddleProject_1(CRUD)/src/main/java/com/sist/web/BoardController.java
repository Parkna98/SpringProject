package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller // 화면 변경 => forward (request전송) / sendRedirect (재호출)
// 				경로명/파일명(suffix로 jsp는 자동으로붙임) /  redirect: .do => reqeust를 초기화
public class BoardController {
	// 화면 구현만 하는 Controller이기때문에 dao 필요없다 => 데이터는 Rest에서
	@GetMapping("board/list.do")
	public String board_list() {
		return "board/list";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no,Model model) {
		model.addAttribute("no",no);
		return "board/detail";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no,Model model) {
		model.addAttribute("no", no);
		return "board/update";
	}
}
