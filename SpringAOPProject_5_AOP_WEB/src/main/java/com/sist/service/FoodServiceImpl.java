package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO dao;
	@Override
	public List<CategoryVO> foodCategoryData() {
		return dao.foodCategoryData();
	}

	@Override
	public List<FoodVO> foodTop7() {
		return dao.foodTop7();
	}

	@Override
	public List<SeoulVO> seoulTop7() {
		return dao.seoulTop7();
	}

}
