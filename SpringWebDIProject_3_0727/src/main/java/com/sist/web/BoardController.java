package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardVO;

//메모리할당
@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	//목록
	@GetMapping("list.do")
	public String board_list(String page,Model model) {  //list.do?page 에서 page 변수명 일치해야함
		//page는 초기에 null값이기때문에 String으로 줘야함
		//Model model => 전송 객체 (request대신 사용)
		if(page==null) 
			page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashedMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end", end);
		List<BoardVO> list = dao.boardListData(map);
		int totalpage = dao.boardTotalPage();
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list",list);
		return "board/list";
	}
	   
	// Insert
	@GetMapping("insert.do")
	public String board_inset() {
		return "board/insert";
	}
	@PostMapping("insert_ok.do")  //form태그로 데이터를 전달받기 때문에 postMapping
	public String board_insert_ok(BoardVO vo) {   //dispatcherservlet이 vo에 값을 채워서 보내줌
		
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	// Update
	// Delete
	// Detail
	/*
	 *  class Model {
	 *      HttpServletRequest request;
	 *      public void addAttribute(String key, Object obj) {
	 *      	request.setAttribute(key,obj);
	 *      }
	 *  }
	 */
	@GetMapping("detail.do")	//a태그로옴 getMapping
	public String board_detail(int no, Model model) {
		// String no = request.getParameter("no")
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	// Find ==> 동적 쿼리
}
