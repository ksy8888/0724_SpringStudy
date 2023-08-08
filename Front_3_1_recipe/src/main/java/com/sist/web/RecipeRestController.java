package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.service.RecipeService;

@RestController
public class RecipeRestController {
	@Autowired
	private RecipeService service;
	
	@GetMapping(value = "recipe/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String recipe_list(int page) {
		String result="";
		try {
			int curpage = page;
			
			int rowSize=12;
			int start = (curpage*rowSize)-(rowSize-1);
			int end = (curpage*rowSize);
			Map map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			List<RecipeVO> list = service.recipeListData(map);
			
			int totalpage = service.recipeTotalPage();
			map.put("totalpage", totalpage);
			
			final int BLOCK=5;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) {
				endPage = totalpage;
			}
			
			int i=0;
			JSONArray arr = new JSONArray();
			for(RecipeVO vo : list) {
				JSONObject obj = new JSONObject();
				obj.put("no", vo.getNo());
				obj.put("title", vo.getTitle());
				obj.put("poster", vo.getPoster());
				obj.put("chef", vo.getChef());
				
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
				}
				arr.add(obj);
				i++;	
			}
			//
			result = arr.toJSONString();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
