package com.sist.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.StoreDAO;
import com.sist.web.entity.StoreEntity;


@Controller
@RequestMapping("store/")
public class StoreController {
	
	@Autowired
	private StoreDAO dao;
	
	@RequestMapping("store_list")
	public String store_list(String page, String fd,Model model) {
		if(fd==null) {
			fd="신선";
		}
		if(page==null) {
			page="1";
		}
		int curpage = Integer.parseInt(page);
		int rowSize= 9;
		int start = (rowSize*curpage)-rowSize;
		List<StoreEntity> list =dao.storeFindData(fd, start);
		int totalpage = dao.storeFindTotalPage(fd);
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage = totalpage;
		}
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("fd", fd);
		model.addAttribute("main_html", "store/store_list");
	
		return "main";
	}
	
	@GetMapping("store_detail")
	public String store_detail(int pdno,Model model) {
		StoreEntity vo = dao.findByPdno(pdno);
		model.addAttribute("vo", vo);
//		String poster = vo.getPoster();
	//	List<StoreEntity> sList = dao.storePosterData(pdno);
	 //   model.addAttribute("sList", sList);
		
		model.addAttribute("main_html", "store/store_detail");
		return "main";
	}
	
}
