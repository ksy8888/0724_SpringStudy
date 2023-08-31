package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
//				                                          -------- @id의 데이터형
@Repository
public interface FoodDAO extends JpaRepository<FoodEntity,Integer>{
   //public FoodEntity findByFno(int fno);  //상세보기
   //SELECT * FROM food_location WHERE fno=1
   //insert , update , delete
	
	@Query(value="SELECT * FROM food_location WHERE address LIKE CONCAT('%',:address,'%') "
			+ "ORDER BY fno ASC "
			+ "LIMIT :start , 12", nativeQuery=true)  //nativeQuery: 있는 그대로 실행
	public List<FoodEntity> foodFindData(@Param("address") String address,
			@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%')", nativeQuery=true)
	public int foodFindTotalPage(String address);
	
	public FoodEntity findByFno(@Param("fno") Integer fno);
	//SELECT * FROM food_location WHERE fno=10
}
