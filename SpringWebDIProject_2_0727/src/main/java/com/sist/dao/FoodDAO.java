package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
/*	@Select("SELECT cno,title,poster,subject "
			+ "FROM food_category "
			+ "ORDER BY cno ASC")
*/
	public List<CategoryVO> foodCategoryListData() {
		return mapper.foodCategoryListData();
	}
/*
@Select("SELECT fno,name,score,address,phone,type,poster "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
 */
	public List<FoodVO> foodListData(int cno) {
		return mapper.foodListData(cno);
	}
}
