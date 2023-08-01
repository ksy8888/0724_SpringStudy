package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.*;
import java.util.*;

@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;

	/*
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT no,title,poster "
			+ "FROM seoul_location ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
*/
	public List<SeoulVO> seoulLocationListData(Map map) {
		return mapper.seoulLocationListData(map);
	}

	
/*	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_location")
*/
	public int seoulLocationTotalPage() {
		return mapper.seoulLocationTotalPage();
	}
	
	
	//Nature
/*		@Select("SELECT no,title,poster,num "
				+ "FROM (SELECT no,title,poster,rownum as num "
				+ "FROM (SELECT no,title,poster "
				+ "FROM seoul_nature ORDER BY ASC)) "
				+ "WHERE num BETWEEN #{start} AND #{end}")
*/
	
	
		public List<SeoulVO> natureListData(Map map) {
			return mapper.natureListData(map);
		}
		
		//@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_nature")
		public int natureTotalPage() {
			return mapper.natureTotalPage();
		}
		
/*		// / Shop
		@Select("SELECT no,title,poster,num "
				+ "FROM (SELECT no,title,poster,rownum as num "
				+ "FROM (SELECT no,title,poster "
				+ "FROM seoul_shop ORDER BY no ASC)) "
				+ "WHERE num BETWEEN #{start} AND #{end}")
*/
		public List<SeoulVO> shopListData(Map map) {
			return mapper.shopListData(map);
		}
	//	@Select("SELECT CEIL(COUNT(*)/12.0) FROM seoul_shop")
		public int shopTotalPage() {
			return mapper.shopTotalPage();
		}
	
}
