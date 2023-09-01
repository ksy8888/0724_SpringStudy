package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
@Repository
public interface FoodCategoryDAO extends JpaRepository<CategoryVO, Integer> {
	//                                                    컬럼    프라이머리키의 자료형
	
	@Query(value = "SELECT cno,title,poster,subject,link FROM food_category",nativeQuery = true)
	public List<CategoryVO> categoryListData();
}
