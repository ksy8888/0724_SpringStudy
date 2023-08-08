package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.FoodDAO;
import java.util.*;
import com.sist.vo.*;

@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value = "food/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list_vue(String page) {
		String result="";
		try {
			if(page==null) {
				page="1";
			}
			int curpage=Integer.parseInt(page);
			Map map = new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			List<FoodLocationVO> list = dao.foodListData(map);
			int totalpage = dao.foodTotalPage();
			
			//List => [] => JSONArray
			//FoodLocationVO => {} => JSONObject
			// [{},{},{}....]
			JSONArray arr = new JSONArray();
			int i=0;
			//fno,name,poster,score
			/*
			 * {fno:1,name:"",poster:"",score:5.0}
			 */
			for(FoodLocationVO vo:list) {
				JSONObject obj=new JSONObject();
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster=vo.getPoster();
				poster = poster.substring(0, poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster", poster);
				if(i==0) {
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
				}
				
				arr.add(obj);
				i++;
				
			}
			result = arr.toJSONString();
			
		} catch (Exception e) {}
		return result;
	}
	/*
	 * 1. 일반 문자열 => NOID,NOPWD,OK => text/html
	 * 2. 데이터묶음(JSON) => text/plain
	 *     List / VO
	 *     ---    --	
	 *      []    {} => JSONObject
	 *     =>JSONArray
	 *     ----------------------------> jackson(Spring-Boot)
	 * 3. XML전송 => text/xml
	 */
	//검색
	@GetMapping(value = "food/find_vue.do",produces = "text/plain;charset=UTF-8")	//axios주소와 매칭
	public String food_find(int page,String fd) { //vuescript에서 curpage를 1로 설정, this.curpage로 자동페이지반환했기 때문에 int로 받음
		String result ="";
		try {
			int curpage=page;
			Map map = new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			map.put("address", fd);
			List<FoodLocationVO> list = dao.foodFindData(map);
			int totalpage = dao.foodFindTotalPage(fd);
			final int BLOCK=5;
			int startPage = ((curpage-1)/BLOCK*BLOCK)+1;		
			int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage) {
				endPage=totalpage;
			}
			
			//JSON 변경
			int i=0;
			JSONArray arr = new JSONArray();  // List대신
			for(FoodLocationVO vo : list) {
				JSONObject obj = new JSONObject();	//VO대신
				//fno,name,poster,score
				obj.put("fno", vo.getFno());
				obj.put("name", vo.getName());
				obj.put("score", vo.getScore());
				String poster=vo.getPoster();
				poster=poster.substring(0, poster.indexOf("^"));
				poster=poster.replace("#", "&");
				obj.put("poster", poster);
				
				if(i==0) { // 배열0번째에 값 실어ㅈ뭄
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
				}
				i++;
				arr.add(obj);
			}
			// [{curpage,totalpage,startPage},{},{},....] 페이지들을 배열의 0번째에 전부 집어넣음 >> vue .then문장에서
			result = arr.toJSONString();
		} catch (Exception e) {e.printStackTrace();}
		return result;
		
	}
}
