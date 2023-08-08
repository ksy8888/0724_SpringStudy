package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.sist.mapper.RecipeMapper;
import com.sist.service.*;
import com.sist.vo.RecipeVO;
///dao로해서 틀림
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
/*	@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum as num "
			+ "FROM (SELECT no,title,poster,chef "
			+ "FROM recipe ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
*/	public List<RecipeVO> recipeListData(Map map) {
		return mapper.recipeListData(map);
	}
	
	//@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
}
