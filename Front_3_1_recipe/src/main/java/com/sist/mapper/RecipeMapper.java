package com.sist.mapper;

import org.apache.ibatis.annotations.Select;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

public interface RecipeMapper {

	@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum as num "
			+ "FROM (SELECT no,title,poster,chef "
			+ "FROM recipe ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage();
}
