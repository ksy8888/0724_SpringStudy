package com.sist.service;

import java.util.List;

import com.sist.vo.*;
//servie :
//Controller의 요청에 맞추어 Repository에서 받은 정보를 가공하여 Controller에게 넘겨주는 비지니스 로직
public interface FoodService {
	
	public List<CategoryVO> foodCategoryData();
	public List<FoodVO> foodTop7();
	public List<SeoulVO> seoulTop7();
	
}
