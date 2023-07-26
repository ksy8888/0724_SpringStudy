package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;

@Repository   //메모리할당ㄴ
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;


	public List<FoodVO> foodCategoryListData(int cno) {
		return mapper.foodCategoryListData(cno);
	}
	

	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
