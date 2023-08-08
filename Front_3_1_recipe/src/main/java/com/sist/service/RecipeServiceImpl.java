package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.RecipeVO;

@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeDAO dao;

	@Override
	public List<RecipeVO> recipeListData(Map map) {		
		return dao.recipeListData(map);
	}
	

	@Override
	public int recipeTotalPage() {
		
		return dao.recipeTotalPage();
	}
	
	
}
