package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.StoreEntity;
import java.util.*;
@Repository
public interface StoreDAO  extends JpaRepository<StoreEntity, Integer>{
	@Query(value="SELECT * FROM product_detail "
			+ "WHERE subject LIKE CONCAT('%',:subject,'%') "
			+ "LIMIT :start , 9", nativeQuery=true)
	public List<StoreEntity> storeFindData(@Param("subject") String subject, @Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/9.0) FROM product_detail WHERE subject LIKE CONCAT('%',:subject,'%')", nativeQuery=true)
	public int storeFindTotalPage(String subject);
	
	@Query(value="SELECT poster FROM product_detail", nativeQuery=true)
	public List<StoreEntity> storePosterData(@Param("pdno") Integer pdno);
	
	public StoreEntity findByPdno(@Param("pdno") Integer pdno);
}
