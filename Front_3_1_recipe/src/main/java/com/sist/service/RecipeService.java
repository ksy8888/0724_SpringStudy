package com.sist.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface RecipeService {
	
/*	@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum as num "
			+ "FROM (SELECT no,title,poster,chef "
			+ "FROM recipe ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
*/	public List<RecipeVO> recipeListData(Map map);
	
	//@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage();
	
}
