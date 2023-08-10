package com.sist.mapper;
import java.util.*;

import com.sist.vo.*;

public interface FoodMapper {
//동적쿼리 등 복잡한 것들은 xml 사용>> dao패키지에 food-mapper.xml	
	//<select id="foodCategoryListData" resultType="CategoryVO" parameterType="hashmap">
	//            -------------------- 			    ----------	               -------
	//               메ㅔ소드명                           리턴형	                    매개변수
	public List<CategoryVO> foodCategoryListData(Map map);	
	
	

   //<select id="foodCategoryInfoData" resultType="CategoryVO" parameterType="int">
	public CategoryVO foodCategoryInfoData(int cno);
	
	//<select id="foodListData" resultType="FoodVO" parameterType="int">
	public List<FoodVO> foodListData(int cno);
	
}
