package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;

@Controller
public class SeoulController {

	@Autowired
	private SeoulDAO dao;
	
	@RequestMapping("seoul/location")
	public String seoul_find(String page, String fd, Model model) {
		if(fd==null) {
			fd="마포";
		}
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize);	//Limit은 0부터 시작 >> 뒤에 -1 뺌
		int end = rowSize*curpage;
		map.put("start", start);
		map.put("address", fd);
		//List<FoodVO> list = service.foodFindData(map);
		//int totalpage=service.foodFindTotalPage(fd);
		List<SeoulLocationEntity> slist = dao.seoulFindData(fd, start);
		int totalpage = dao.seoulTotalPage(fd);
		
		
		final int BLOCK = 5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("slist", slist);
		model.addAttribute("fd", fd);
		return "seoul/location";
	}
}
