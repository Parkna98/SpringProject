package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// Vue로 데이터를 전송 => [],{}
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class FoodController {
	@GetMapping("food/list.do")
	public String food_list() {
		
		
		return "food/list"; // Controller에서는 파일명만 보내줌 (forward/sendRedirect)
		// JSON 데이터를 보내고싶다면 RestController
	}
}
