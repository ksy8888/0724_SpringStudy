package com.sist.web;

import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food/category.do")
	//4.3 => GET / POST / => (GET,POST)
	public String food_category(String cno, Model model) {
		//상세보기 => int로 넘어옴
		//category.do?cno=1 (X), 사용자가 처음에 값을 주고 들어오지않음. 초기값은 null >> string으로 받음
		if(cno==null) {
			cno="1";
		}
		Map map = new HashedMap();
		map.put("cno", Integer.parseInt(cno));
		List<CategoryVO> list = dao.categoryListData(map);
		model.addAttribute("list", list);
		return "food/category";
	}
}
